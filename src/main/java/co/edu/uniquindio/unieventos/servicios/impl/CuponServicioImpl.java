package co.edu.uniquindio.unieventos.servicios.impl;

import co.edu.uniquindio.unieventos.dto.cupon.ActualizarCuponDTO;
import co.edu.uniquindio.unieventos.dto.cupon.CrearCuponDTO;
import co.edu.uniquindio.unieventos.dto.cupon.ItemCuponDTO;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuponServicio;

public class CuponServicioImpl implements CuponServicio {

    @Override
    public String crearCupones(CrearCuponDTO cuponDTO) {
        return "";
    }

    @Override
    public String actualizarCupon(ActualizarCuponDTO cuponDTO) {
        return "";
    }

    @Override
    public void borrarCupon(String idCupon) {

    }

    @Override
    public boolean validarCupon(String idCupon, String userId) {
        return false;
    }

    @Override
    public String redimirCupon(String idCupon, String idCliente) {
        return null;
    }

    @Override
    public ItemCuponDTO listarCupones(String idCupon, String idCliente) {
        return null;
    }
}
