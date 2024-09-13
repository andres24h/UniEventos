package co.edu.uniquindio.unieventos.servicios.impl;

import co.edu.uniquindio.unieventos.documentos.*;
import co.edu.uniquindio.unieventos.dto.cuenta.*;
import co.edu.uniquindio.unieventos.repositorios.CuentaRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuentaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CuentaServicioImpl implements CuentaServicio {
    private final CuentaRepo cuentaRepo;
    private String ejemplo;

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
        nuevaCuenta.setPassword(cuenta.password());
        nuevaCuenta.setFechaRegistro(LocalDateTime.now());
        nuevaCuenta.setCodigoValidacionRegistro(new CodigoValidacion(generarCodigo(), LocalDateTime.now()));
        nuevaCuenta.setUsuario(new Usuario(
                cuenta.cedula(),
                cuenta.nombre(),
                cuenta.direccion(),
                cuenta.telefonos()
        ));
        Cuenta cuentaCreada = cuentaRepo.save(nuevaCuenta);
        return cuentaCreada.getUsuario().getCedula();
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
            throw new Exception("El código de verificación ha expirado");
        }

        cuenta.setEstado(EstadoCuenta.ACTIVO);
        cuenta.setCodigoValidacionRegistro(null);
        cuentaRepo.save(cuenta);

        return true;
    }



    @Override
    public String editarCuenta(EditarCuentaDTO cuenta) throws Exception {
        Optional<Cuenta> cuentaOptional = cuentaRepo.buscarId(cuenta.id());
        if (cuentaOptional.isEmpty()) {
            throw new Exception("No existe la cuenta");
        }
        Cuenta cuentaModificada = cuentaOptional.get();
        if (!existeCedula(cuenta.id())) {
            throw new Exception("No se encontro la cuenta con la cedula registrada" + cuenta.id());
        }
        cuentaModificada.getUsuario().setNombre(cuenta.nombre());
        cuentaModificada.getUsuario().setDireccion(cuenta.direccion());
        cuentaModificada.getUsuario().setTelefonos(cuenta.telefonos());

        cuentaRepo.save(cuentaModificada);
        return cuentaModificada.getId();
    }

    @Override
    public String eliminarCuenta(String id) throws Exception {
        Optional<Cuenta> cuentaOptional = cuentaRepo.buscarId(id);
        if (cuentaOptional.isEmpty()) {
            throw new Exception("La cuenta no existe");
        }
        if (!existeCedula(id)) {
            throw new Exception("No se encontro la cuenta:" + id);
        }
        Cuenta cuenta = cuentaOptional.get();
        cuenta.setEstado(EstadoCuenta.ELIMINADO);

        cuentaRepo.save(cuenta);
        return id;

    }


    @Override
    public InformacionCuentaDTO obtenerInformacionCuenta(String id) throws Exception {
        Cuenta cuenta = obtenerCuenta(id);
        return new InformacionCuentaDTO(
                cuenta.getUsuario().getCedula(),
                cuenta.getUsuario().getNombre(),
                cuenta.getUsuario().getTelefonos(),
                cuenta.getUsuario().getDireccion(),
                cuenta.getEmail()
        );
    }

    private Cuenta obtenerCuenta(String id) throws Exception {
        Optional<Cuenta> cuentaOptional = cuentaRepo.buscarId(id);

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
        String codigoValidacion = generarCodigo();


        cuenta.setCodigoValidacionPassword(new CodigoValidacion(codigoValidacion,
                LocalDateTime.now()));
        cuentaRepo.save(cuenta);

        return "se ha enviado un correo de validacion";
    }

    @Override
    public String cambiarPassword(CambiarPasswordDTO cambiarPasswordDTO) throws Exception {
        Optional<Cuenta> cuentaOptional = cuentaRepo.buscarEmail(cambiarPasswordDTO.email());

        if (cuentaOptional.isEmpty()) {
            throw new Exception("El correo dado no esta registrado");
        }
        Cuenta cuenta = cuentaOptional.get();
        CodigoValidacion codigoValidacion = cuenta.getCodigoValidacionPassword();

        if (codigoValidacion.getCodigo().equals(cambiarPasswordDTO.codigoVerificacion())) {
            if (codigoValidacion.getFechaCreacion().
                    plusMinutes(15).isBefore(LocalDateTime.now())) {
                cuenta.setPassword(cambiarPasswordDTO.passwordNueva());
                cuentaRepo.save(cuenta);
                return "contraseña cambiada correctamente";
            } else {
                throw new Exception("El codigo de validacion ya expiro");
            }

        } else {
            throw new Exception("El codigo de validacion es incorrecto");
        }
    }


    @Override
    public String iniciarSesion(LoginDTO loginDTO) throws Exception {
        Optional<Cuenta> cuentaOptional = cuentaRepo.buscarEmail(loginDTO.correo());

        if (cuentaOptional.isEmpty()) {
            throw new Exception("El correo dado no esta registrado");
        }

        Cuenta cuenta = cuentaOptional.get();

        if (cuenta.getPassword().equals(loginDTO.password())) {
            cuenta.setPassword(loginDTO.password());
            cuentaRepo.save(cuenta);
            return "Token";
        }
        return "";
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
