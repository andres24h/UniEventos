package co.edu.uniquindio.unieventos.servicios.impl;

import co.edu.uniquindio.unieventos.config.JWTUtils;
import co.edu.uniquindio.unieventos.documentos.*;
import co.edu.uniquindio.unieventos.dto.cuenta.*;
import co.edu.uniquindio.unieventos.dto.email.EmailDTO;
import co.edu.uniquindio.unieventos.repositorios.CuentaRepo;
import co.edu.uniquindio.unieventos.repositorios.EventoRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuentaServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.EmailServicio;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CuentaServicioImpl implements CuentaServicio {
    private final CuentaRepo cuentaRepo;
    private final JWTUtils jwtUtils;
    private final EmailServicio emailServicio;
    private final EventoRepo eventoRepo;

    @Override
    public String crearCuenta(CrearCuentaDTO cuenta) throws Exception {
        if (existeEmail(cuenta.email())) {
            throw new Exception("El correo:" + cuenta.email() + " ya existe");
        }

        if (existeCedula(cuenta.cedula())) {
            throw new Exception("La cedula:" + cuenta.cedula() + " ya existe");
        }

        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setId(cuenta.cedula());
        nuevaCuenta.setRol(Rol.CLIENTE);
        nuevaCuenta.setEstado(EstadoCuenta.INACTIVO);
        nuevaCuenta.setEmail(cuenta.email());
        String passwordEncriptada =encriptarPassword(cuenta.password());
        nuevaCuenta.setPassword(passwordEncriptada);
        nuevaCuenta.setFechaRegistro(LocalDateTime.now());

        nuevaCuenta.setCodigoValidacionRegistro(new CodigoValidacion(generarCodigo(), LocalDateTime.now()));
        EmailDTO emailDTO=new EmailDTO("Su codigo de activacion es:"+nuevaCuenta.getCodigoValidacionRegistro(),"ingrese el codigo para poder activar su cuenta",cuenta.email());

        emailServicio.enviarCorreo(emailDTO);
        nuevaCuenta.setUsuario(new Usuario(
                cuenta.nombre(),
                cuenta.direccion(),
                cuenta.telefonos()
        ));
        Cuenta cuentaCreada = cuentaRepo.save(nuevaCuenta);
        return cuentaCreada.getId();
    }

    @Override
    public boolean activarCuenta(ActivarCuentaDTO activarCuentaDTO) throws Exception {
        Optional<Cuenta> cuentaOptional = cuentaRepo.buscarEmail(activarCuentaDTO.email());

        if (cuentaOptional.isEmpty()) {
            throw new Exception("Cuenta no existe");
        }

        Cuenta cuenta = cuentaOptional.get();

        if (!activarCuentaDTO.codigoVerificacion().equals(cuenta.getCodigoValidacionRegistro().getCodigo())) {
            throw new Exception("Código de verificación incorrecto");
        }

        LocalDateTime fechaCreacion = cuenta.getCodigoValidacionRegistro().getFechaCreacion();
        LocalDateTime fechaActual = LocalDateTime.now();
        Duration duracionValidez = Duration.ofMinutes(15);

        if (Duration.between(fechaCreacion, fechaActual).compareTo(duracionValidez) > 0) {
            cuenta.setCodigoValidacionRegistro(new CodigoValidacion(generarCodigo(), LocalDateTime.now()));
            EmailDTO emailDTO=new EmailDTO("Su codigo de activacion es:"+cuenta.getCodigoValidacionRegistro(),"ingrese el codigo para poder activar su cuenta",cuenta.getEmail());

            emailServicio.enviarCorreo(emailDTO);
            cuentaRepo.save(cuenta);
            throw new Exception("El código de verificación ha expirado");

        }

        cuenta.setEstado(EstadoCuenta.ACTIVO);
        cuenta.setCodigoValidacionRegistro(null);
        cuentaRepo.save(cuenta);

        return true;
    }



    @Override
    public String editarCuenta(EditarCuentaDTO cuenta) throws Exception {
        Optional<Cuenta> cuentaOptional = cuentaRepo.findById(cuenta.id());
        if (cuentaOptional.isEmpty()) {
            throw new Exception("No existe la cuenta");
        }
        Cuenta cuentaModificada = cuentaOptional.get();
        if (!existeCedula(cuenta.id())) {
            throw new Exception("No se encontro la cuenta con la cedula registrada" + cuenta.id());
        }
        if(cuentaModificada.getEstado().equals(EstadoCuenta.INACTIVO)){
            throw new Exception("La cuenta se encuentra inactiva");
        }
        if(cuentaModificada.getEstado().equals(EstadoCuenta.ELIMINADO)){
            throw new Exception("La cuenta se encuentra inactiva");
        }
        cuentaModificada.getUsuario().setNombre(cuenta.nombre());
        cuentaModificada.getUsuario().setDireccion(cuenta.direccion());
        cuentaModificada.getUsuario().setTelefonos(cuenta.telefonos());

        cuentaRepo.save(cuentaModificada);
        return cuentaModificada.getId();
    }

    @Override
    public String eliminarCuenta(String id) throws Exception {
        Optional<Cuenta> cuentaOptional = cuentaRepo.findById(id);
        if (cuentaOptional.isEmpty()) {
            throw new Exception("La cuenta no existe");
        }
        Cuenta cuenta = cuentaOptional.get();
        if (cuenta.getEstado().equals(EstadoCuenta.ELIMINADO)) {
            throw new Exception("La cuenta ya ha sido eliminada:" + id);
        }
        //Aunque sabemos que el frontend no permite el acceso hasta aqui
        if (cuenta.getEstado().equals(EstadoCuenta.INACTIVO)) {
            throw new Exception("La cuenta no ha sido activada:" + id);
        }
        cuenta.setEstado(EstadoCuenta.ELIMINADO);

        cuentaRepo.save(cuenta);
        return id;

    }


    @Override
    public InformacionCuentaDTO obtenerInformacionCuenta(String id) throws Exception {
        Cuenta cuenta = obtenerCuenta(id);
        return new InformacionCuentaDTO(
                cuenta.getId(),
                cuenta.getUsuario().getNombre(),
                cuenta.getUsuario().getTelefonos(),
                cuenta.getUsuario().getDireccion(),
                cuenta.getEmail()
        );
    }

    private Cuenta obtenerCuenta(String id) throws Exception {
        Optional<Cuenta> cuentaOptional = cuentaRepo.findById(id);

        if (cuentaOptional.isEmpty()) {
            throw new Exception("No existe la cuenta");
        }
        return cuentaOptional.get();

    }

    @Override
    public String enviarCodigoRecuperacionPassword(String correo) throws Exception {
        Optional<Cuenta> cuentaOptional = cuentaRepo.buscarEmail(correo);

        if (cuentaOptional.isEmpty()) {
            throw new Exception("El correo dado no se encuentra registrado");
        }
        Cuenta cuenta = cuentaOptional.get();
        if(cuenta.getEstado().equals(EstadoCuenta.ELIMINADO)){
            throw new Exception("La cuenta ha sido eliminada");
        }
        if(cuenta.getEstado().equals(EstadoCuenta.INACTIVO)){ //Aunque sabemos que el frontend no permite el acceso hasta aqui
            throw new Exception("La cuenta no ha sido activada");
        }
        String codigoValidacion = generarCodigo();


        cuenta.setCodigoValidacionPassword(new CodigoValidacion(codigoValidacion,
                LocalDateTime.now()));
        EmailDTO email=new EmailDTO("codigo recuperacion password","ingrese solo el codigo:"+cuenta.getCodigoValidacionPassword(),correo);
        emailServicio.enviarCorreo(email);
        cuentaRepo.save(cuenta);

        return "se ha enviado un correo de validacion";
    }

    @Override
    public String cambiarPassword(CambiarPasswordDTO cambiarPasswordDTO) throws Exception {
        Optional<Cuenta> cuentaOptional = cuentaRepo.buscarEmail(cambiarPasswordDTO.email());

        if (cuentaOptional.isEmpty()) {
            throw new Exception("El correo dado no está registrado");
        }
        Cuenta cuenta = cuentaOptional.get();
        if (cuenta.getEstado().equals(EstadoCuenta.INACTIVO)) {
            throw new Exception("Su cuenta no se encuentra activa");
        }
        if (cuenta.getEstado().equals(EstadoCuenta.ELIMINADO)) {
            throw new Exception("Su cuenta esta eliminada");
        }
        CodigoValidacion codigoValidacion = cuenta.getCodigoValidacionPassword();

        if (codigoValidacion.getCodigo().equals(cambiarPasswordDTO.codigoVerificacion())) {
            LocalDateTime fechaCreacion = codigoValidacion.getFechaCreacion();
            if (Duration.between(fechaCreacion, LocalDateTime.now()).toMinutes() <= 15) {
                String passwordEncriptada= encriptarPassword(cambiarPasswordDTO.passwordNueva());
                cuenta.setPassword(passwordEncriptada);

                CodigoValidacion codigoValidacion1=new CodigoValidacion();
                cuenta.setCodigoValidacionPassword(codigoValidacion1);
                cuentaRepo.save(cuenta);

                return "contraseña cambiada correctamente";
            } else {
                throw new Exception("El código de validación ya expiró");
            }
        } else {
            throw new Exception("El código de validación es incorrecto");
        }
    }


    @Override
    public TokenDTO iniciarSesion(LoginDTO loginDTO) throws Exception {
        Optional<Cuenta> cuentaOptional= cuentaRepo.buscarEmail(loginDTO.correo());
        if(cuentaOptional.isEmpty()){
            throw new Exception("No existe la cuenta");
        }
        Cuenta cuenta=cuentaOptional.get();
        if(cuenta.getEstado().equals(EstadoCuenta.ELIMINADO)){
            throw new Exception("La cuenta ya fue eliminada");
        }
        if(cuenta.getEstado().equals(EstadoCuenta.INACTIVO)){
            throw new Exception("La cuenta se encuentra inactiva");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if( !passwordEncoder.matches(loginDTO.password(), cuenta.getPassword()) ) {
            throw new Exception("La contraseña es incorrecta");
        }

        Map<String, Object> map = construirClaimsCliente(cuenta);
        return new TokenDTO( jwtUtils.generarToken(cuenta.getEmail(), map) );
    }

    private Map<String, Object> construirClaimsCliente(Cuenta cuenta) {
        return Map.of(
                "rol", cuenta.getRol(),
                "nombre", cuenta.getUsuario().getNombre(),
                "id", cuenta.getId()
        );
    }


    private String encriptarPassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode( password );
    }

    @Override
    public String agregarEventoCarrito(AgregarEventoDTO agregarEventoDTO) throws Exception {
        Optional<Cuenta> cuentaOptional = cuentaRepo.findById(agregarEventoDTO.idUsuario());
        if (cuentaOptional.isEmpty()) {
            throw new Exception("La cuenta no existe");
        }

        Cuenta cuenta = cuentaOptional.get();

        if (!cuenta.getRol().equals(Rol.CLIENTE)) {
            throw new Exception("El usuario no tiene  carrito");
        }

        if (cuenta.getEstado().equals(EstadoCuenta.ELIMINADO)) {
            throw new Exception("La cuenta ha sido eliminada");
        }

        Optional<Evento> optionalEvento = eventoRepo.findById(agregarEventoDTO.idEvento());
        if(optionalEvento.isEmpty()){
            throw new Exception("El evento no fue encontrado");
        }

        Evento evento=optionalEvento.get();

        Localidad localidad = evento.getLocalidades().stream()
                .filter(l -> l.getNombre().equals(agregarEventoDTO.nombreLocalidad()))
                .findFirst().orElseThrow(() -> new Exception("La localidad no existe"));

        if (localidad.cantidadDisponible() < agregarEventoDTO.cantidad()) {
            throw new Exception("No hay suficientes entradas disponibles en este momento hay" +
                    +localidad.cantidadDisponible());
        }

        Carrito carrito = cuenta.getCarrito();
        if (carrito == null) {
            carrito = new Carrito();
            carrito.setFecha(LocalDateTime.now());
            cuenta.setCarrito(carrito);
        }

        DetalleCarrito detalleCarrito = new DetalleCarrito();
        detalleCarrito.setCodigoDetalle(new ObjectId());
        detalleCarrito.setIdEvento(agregarEventoDTO.idEvento());
        detalleCarrito.setCantidad(agregarEventoDTO.cantidad());
        detalleCarrito.setNombreLocalidad(agregarEventoDTO.nombreLocalidad());

        List<DetalleCarrito> detalleCarritos = carrito.getItems();
        if (detalleCarritos == null) {
            detalleCarritos = new ArrayList<>();
            carrito.setItems(detalleCarritos);
        }
        detalleCarritos.add(detalleCarrito);

        cuentaRepo.save(cuenta);

        return "Evento agregado al carrito con éxito";
    }


    @Override
    public String eliminarEventoCarrito(EliminarEventoDTO eliminarEventoDTO) throws Exception {
        Optional<Cuenta> cuentaOptional = cuentaRepo.findById(eliminarEventoDTO.idCliente());
        if (cuentaOptional.isEmpty()) {
            throw new Exception("La cuenta no existe");
        }

        Cuenta cuenta = cuentaOptional.get();

        if (!cuenta.getRol().equals(Rol.CLIENTE)) {
            throw new Exception("El usuario no tiene un carrito asignado");
        }

        if (cuenta.getEstado().equals(EstadoCuenta.ELIMINADO)) {
            throw new Exception("La cuenta ha sido eliminada");
        }
        if (cuenta.getEstado().equals(EstadoCuenta.INACTIVO)) {
            throw new Exception("Su cuenta no se encuentra activa");
        }


        Carrito carrito = cuenta.getCarrito();
        if (carrito == null || carrito.getItems().isEmpty()) {
            return "El carrito está vacío";
        }

        List<DetalleCarrito> detalles = carrito.getItems();
        Optional<DetalleCarrito> detalleCarritoOptional = detalles.stream()
                .filter(detalle -> detalle.getCodigoDetalle().equals(eliminarEventoDTO.idDetalle()))
                .findFirst();

        if (detalleCarritoOptional.isEmpty()) {
            throw new Exception("El evento no está en el carrito");
        }

        detalles.remove(detalleCarritoOptional.get());

        cuentaRepo.save(cuenta);

        return "Evento eliminado del carrito correctamente";
    }

    @Override
    public List<ItemCuentaDTO> listarCuentas() {

        List<Cuenta> cuentas = cuentaRepo.findAll();

        List<ItemCuentaDTO> items = new ArrayList<>();

        for (Cuenta cuenta : cuentas) {
            items.add( new ItemCuentaDTO(
                    cuenta.getId(),
                    cuenta.getUsuario().getNombre(),
                    cuenta.getEmail(),
                    cuenta.getUsuario().getTelefonos()
            ));
        }
        return items;
    }

    private boolean existeEmail(String email) {
        return cuentaRepo.buscarEmail(email).isPresent();
    }

    private boolean existeCedula(String cedula) {
        return cuentaRepo.buscarId(cedula).isPresent();
    }

    @Override
    public String generarCodigo() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int tam = 10;
        SecureRandom random = new SecureRandom();
        StringBuilder codigo= new StringBuilder(tam);
        for (int i = 0; i < tam; i++) {
            int index = random.nextInt(caracteres.length());
            codigo.append(caracteres.charAt(index));
        }
        return codigo.toString();
    }

}
