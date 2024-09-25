package co.edu.uniquindio.unieventos.servicios.impl;

import co.edu.uniquindio.unieventos.documentos.*;
import co.edu.uniquindio.unieventos.dto.orden.CrearOrdenDTO;
import co.edu.uniquindio.unieventos.dto.orden.ItemOrdenDTO;
import co.edu.uniquindio.unieventos.dto.orden.OrdenDTO;
import co.edu.uniquindio.unieventos.repositorios.OrdenRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.EventoServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.OrdenServicio;
import com.mercadopago.MercadoPagoConfig;
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
import java.util.stream.Collectors;

@Service
@Transactional
public class OrdenServicioImpl implements OrdenServicio {

    @Autowired
    private EventoServicio eventoServicio;
    @Autowired
    private OrdenRepo ordenRepo;


    @Override
    public Preference realizarPago(String idOrden) throws Exception {
        return null;
    }

    @Override
    public void recibirNotificacionMercadoPago(Map<String, Object> request) {

    }

    @Override
    public String crearOrden(CrearOrdenDTO crearOrdenDTO) {
      return "";
    }

    @Override
    public OrdenDTO obtenerOrden(String idOrden) {
        return null;
    }

    @Override
    public List<OrdenDTO> listOrdenByUsuario(String idUsuario) {
        return List.of();
    }

    @Override
    public void cancelarOrden(String idOrden) {

    }

    @Override
    public void procesarPago(String noclaro) {

    }

    @Override
    public String generarQRC(String idOrden) {
        return "";
    }
}

