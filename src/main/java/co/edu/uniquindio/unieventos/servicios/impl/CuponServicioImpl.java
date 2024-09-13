package co.edu.uniquindio.unieventos.servicios.impl;

import co.edu.uniquindio.unieventos.dto.cupon.CuponDTO;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuponServicio;

public class CuponServicioImpl implements CuponServicio {
    @Override
    public String crearCupones(CuponDTO cuponDTO) {
        return "";
    }

    @Override
    public CuponDTO actualizarCupon(CuponDTO cuponDTO) {
        return null;
    }

    @Override
    public void borrarCupon(String idCupon) {

    }

    @Override
    public boolean validarCupon(String idCupon, String userId) {
        return false;
    }

    @Override
    public CuponDTO redimirCupon(String idCupon, String idCliente) {
        return null;
    }
}
