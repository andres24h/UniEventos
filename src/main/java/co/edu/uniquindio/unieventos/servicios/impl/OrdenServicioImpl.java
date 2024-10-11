package co.edu.uniquindio.unieventos.servicios.impl;

import co.edu.uniquindio.unieventos.documentos.*;
import co.edu.uniquindio.unieventos.dto.cupon.RedimirCuponDTO;
import co.edu.uniquindio.unieventos.dto.cupon.RevertirCuponDTO;
import co.edu.uniquindio.unieventos.dto.evento.InformacionEventoDTO;
import co.edu.uniquindio.unieventos.dto.orden.CrearOrdenDTO;
import co.edu.uniquindio.unieventos.dto.orden.ItemOrdenDTO;
import co.edu.uniquindio.unieventos.repositorios.CuentaRepo;
import co.edu.uniquindio.unieventos.repositorios.CuponRepo;
import co.edu.uniquindio.unieventos.repositorios.EventoRepo;
import co.edu.uniquindio.unieventos.repositorios.OrdenRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuponServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.EventoServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.OrdenServicio;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
    @Transactional
    public class OrdenServicioImpl implements OrdenServicio {

        @Autowired
        private EventoServicio eventoServicio;
        @Autowired
        private EventoRepo eventoRepo;
        @Autowired
        private OrdenRepo ordenRepo;
        @Autowired
        private CuentaRepo cuentaRepo;

        @Autowired
        private CuponRepo cuponRepo;

        @Autowired
        private CuponServicio cuponServicio;

        @Override
        public Preference realizarPago(String idOrden) throws Exception {

            Orden ordenGuardada = obtenerOrden(idOrden);
            List<PreferenceItemRequest> itemsPasarela = new ArrayList<>();


            for (DetalleOrden item : ordenGuardada.getItems()) {

                InformacionEventoDTO informacionEventoDTO = eventoServicio.obtenerInformacionEvento(item.getIdEvento().toString());


                Localidad localidad = informacionEventoDTO.localidades().stream()
                        .filter(loc -> loc.getNombre().equals(item.getNombreLocalidad()))
                        .findFirst()
                        .orElseThrow(() -> new Exception("Localidad no encontrada para el evento " + item.getIdEvento()));


                PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                        .id(informacionEventoDTO.id())
                        .title(informacionEventoDTO.nombre())
                        .pictureUrl(informacionEventoDTO.imagenPortada())
                        .categoryId(informacionEventoDTO.tipo().name())
                        .quantity(item.getCantidad())
                        .currencyId("COP")
                        .unitPrice(BigDecimal.valueOf(localidad.getPrecio()))
                        .build();

                itemsPasarela.add(itemRequest);
            }


            MercadoPagoConfig.setAccessToken("APP_USR-2088304176567559-100922-4374d8d26557817225214aef1143cf1c-2027302781");

            PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                    .success("URL_PAGO_EXITOSO")
                    .failure("URL_PAGO_FALLIDO")
                    .pending("URL_PAGO_PENDIENTE")
                    .build();


            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .backUrls(backUrls)
                    .items(itemsPasarela)
                    .metadata(Map.of("id_orden", ordenGuardada.getId()))
                    .notificationUrl("https://1fff-152-202-205-68.ngrok-free.app/api/orden/notificacion")
                    .build();


            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);


            ordenGuardada.setCodigoPasarela(preference.getId());
            ordenRepo.save(ordenGuardada);

            return preference;
        }


        @Override
        public void recibirNotificacionMercadoPago(Map<String, Object> request) {
            try {


                // Obtener el tipo de notificación
                Object tipo = request.get("type");


                // Si la notificación es de un pago entonces obtener el pago y la orden asociada
                if ("payment".equals(tipo)) {


                    // Capturamos el JSON que viene en el request y lo convertimos a un String
                    String input = request.get("data").toString();


                    // Extraemos los números de la cadena, es decir, el id del pago
                    String idPago = input.replaceAll("\\D+", "");


                    // Se crea el cliente de MercadoPago y se obtiene el pago con el id
                    PaymentClient client = new PaymentClient();
                    Payment payment = client.get( Long.parseLong(idPago) );


                    // Obtener el id de la orden asociada al pago que viene en los metadatos
                    String idOrden = payment.getMetadata().get("id_orden").toString();


                    // Se obtiene la orden guardada en la base de datos y se le asigna el pago
                    Orden orden = obtenerOrden(idOrden);
                    Pago pago = crearPago(payment);
                    orden.setPago(pago);
                    ordenRepo.save(orden);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        @Override
        public String crearOrden(CrearOrdenDTO crearOrdenDTO) throws Exception {
            Optional<Cuenta> optionalCuenta = cuentaRepo.findById(crearOrdenDTO.idCliente());
            if (optionalCuenta.isEmpty()) {
                throw new Exception("El cliente no existe");
            }
            Cuenta cuenta = optionalCuenta.get();
            if (cuenta.getEstado().equals(EstadoCuenta.ELIMINADO) || cuenta.getEstado().equals(EstadoCuenta.INACTIVO)) {
                throw new Exception("El cliente no se encuentra disponible");
            }


            Optional<Cupon> optionalCupon = cuponRepo.findByCodigo(crearOrdenDTO.codigoCupon());
            Cupon cupon = null;
            boolean cuponRedimido = false;

            if (optionalCupon.isPresent()) {
                cupon = optionalCupon.get();
                RedimirCuponDTO redimirCuponDTO = new RedimirCuponDTO(crearOrdenDTO.codigoCupon(), crearOrdenDTO.idCliente());
                cuponRedimido = cuponServicio.redimirCupon(redimirCuponDTO);
            }


            Orden orden = new Orden();
            orden.setIdCliente(crearOrdenDTO.idCliente());
            if (cuponRedimido) {
                orden.setIdCupon(cupon.getId());
            }
            orden.setFecha(LocalDateTime.now());

            List<DetalleOrden> detallesOrden = new ArrayList<>();
            float total = 0;


            for (DetalleCarrito itemCarrito : cuenta.getCarrito().getItems()) {
                Evento evento = eventoRepo.findById(itemCarrito.getIdEvento())
                        .orElseThrow(() -> new Exception("Evento no encontrado"));


                Localidad localidad = evento.getLocalidades().stream()
                        .filter(l -> l.getNombre().equals(itemCarrito.getNombreLocalidad()))
                        .findFirst()
                        .orElseThrow(() -> new Exception("Localidad no encontrada"));

                int aforoDisponible = localidad.getCapacidadMaxima() - localidad.getEntradasVendidas();
                if (aforoDisponible < itemCarrito.getCantidad()) {
                    throw new Exception("No hay suficientes entradas disponibles para " + localidad.getNombre());
                }


                localidad.setEntradasVendidas(localidad.getEntradasVendidas() - itemCarrito.getCantidad());


                DetalleOrden detalleOrden = new DetalleOrden();
                detalleOrden.setIdEvento(itemCarrito.getIdEvento());
                detalleOrden.setNombreLocalidad(itemCarrito.getNombreLocalidad());
                detalleOrden.setPrecioUnitario(localidad.getPrecio());
                detalleOrden.setCantidad(itemCarrito.getCantidad());
                detallesOrden.add(detalleOrden);


                total += (float) (localidad.getPrecio() * itemCarrito.getCantidad());
            }

            if (cuponRedimido) {
                double descuento = cupon.getDescuento();
                total -= (float) (total * (descuento / 100));
            }
            orden.setItems(detallesOrden);
            orden.setTotal(total);

            ordenRepo.save(orden);

            return orden.getId();
        }


        @Override
        public Orden obtenerOrden(String idOrden) throws Exception {

            return ordenRepo.findById(idOrden)
                    .orElseThrow(() -> new Exception("La orden no fue encontrada"));
        }

    @Override
    public List<ItemOrdenDTO> listOrdenByEvento(String idEvento) {
        List<Orden> ordenes = ordenRepo.findByItemsIdEvento(idEvento);

        return ordenes.stream().map(orden -> new ItemOrdenDTO(
                orden.getIdCliente(),
                orden.getIdCupon(),
                orden.getTotal(),
                orden.getFecha(),
                orden.getItems()
        )).toList();
    }



    @Override
    public List<ItemOrdenDTO> listOrdenByUsuario(String idCliente) {
        return ordenRepo.listarOrdenes(idCliente);
    }


    private Pago crearPago(Payment payment) {
        Pago pago = new Pago();
        pago.setCodigo(payment.getId().toString());
        pago.setFecha( payment.getDateCreated().toLocalDateTime() );
        pago.setEstado(payment.getStatus());
        pago.setDetalleEstado(payment.getStatusDetail());
        pago.setTipoPago(payment.getPaymentTypeId());
        pago.setMoneda(payment.getCurrencyId());
        pago.setCodigoAutorizacion(payment.getAuthorizationCode());
        pago.setValorTransaccion(payment.getTransactionAmount().floatValue());
        return pago;
    }

}
