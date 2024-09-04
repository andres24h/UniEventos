package co.edu.uniquindio.unieventos.servicios.impl;

import co.edu.uniquindio.unieventos.dto.cuenta.*;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuentaServicio;

public class CuentaServicioImpl implements CuentaServicio {
    @Override
    public String crearCuenta(CrearCuentaDTO cuenta) throws Exception {
        return "";
    }

    @Override
    public boolean activarCuenta(String codigo) {
        return false;
    }

    @Override
    public String editarCuenta(EditarCuentaDTO cuenta) throws Exception {
        return "";
    }

    @Override
    public String eliminarCuenta(String id) throws Exception {
        return "";
    }

    @Override
    public InformacionCuentaDTO obtenerInformacionCuenta(String id) throws Exception {
        return null;
    }

    @Override
    public String enviarCodigoRecuperacionPassword(String correo) throws Exception {
        return "";
    }

    @Override
    public String cambiarPassword(CambiarPasswordDTO cambiarPasswordDTO) throws Exception {
        return "";
    }

    @Override
    public String iniciarSesion(LoginDTO loginDTO) throws Exception {
        return "";
    }
}
