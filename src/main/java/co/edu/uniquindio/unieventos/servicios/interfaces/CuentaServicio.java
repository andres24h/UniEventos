package co.edu.uniquindio.unieventos.servicios.interfaces;


import co.edu.uniquindio.unieventos.documentos.CodigoValidacion;
import co.edu.uniquindio.unieventos.dto.cuenta.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface CuentaServicio {

    String crearCuenta(CrearCuentaDTO cuenta) throws Exception;

    boolean activarCuenta(ActivarCuentaDTO activarCuentaDTO) throws Exception;

    String generarCodigo();

    String editarCuenta(EditarCuentaDTO cuenta) throws Exception;

    String eliminarCuenta(String id) throws Exception;

    InformacionCuentaDTO obtenerInformacionCuenta(String id) throws Exception;

    String enviarCodigoRecuperacionPassword(String correo) throws Exception;

    String cambiarPassword(CambiarPasswordDTO cambiarPasswordDTO) throws Exception;

    TokenDTO iniciarSesionCliente(LoginDTO loginDTO) throws Exception;

    TokenDTO iniciarSesionAdmin(LoginDTO loginDTO) throws Exception;

    String agregarEventoCarrito(AgregarEventoDTO agregarEventoDTO)throws Exception;

    String eliminarEventoCarrito(EliminarEventoDTO eliminarEventoDTO)throws Exception;

    List<ItemCuentaDTO> listarCuentas();

}