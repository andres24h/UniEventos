package co.edu.uniquindio.unieventos.servicios.interfaces;


import co.edu.uniquindio.unieventos.documentos.CodigoValidacion;
import co.edu.uniquindio.unieventos.dto.cuenta.*;

public interface CuentaServicio {

    String crearCuenta(CrearCuentaDTO cuenta) throws Exception;

    boolean activarCuenta(ActivarCuentaDTO activarCuentaDTO) throws Exception;

    String generarCodigo();

    String editarCuenta(EditarCuentaDTO cuenta) throws Exception;

    String eliminarCuenta(String id) throws Exception;

    InformacionCuentaDTO obtenerInformacionCuenta(String id) throws Exception;

    String enviarCodigoRecuperacionPassword(String correo) throws Exception;

    String cambiarPassword(CambiarPasswordDTO cambiarPasswordDTO) throws Exception;

    String iniciarSesion(LoginDTO loginDTO) throws Exception;

}