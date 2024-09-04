package co.edu.uniquindio.unieventos.servicios;

import co.edu.uniquindio.unieventos.dto.cupon.CuponDTO;

public interface CuponServicio {
    String crearCupones(CuponDTO cuponDTO);
    CuponDTO actualizarCupon(CuponDTO cuponDTO);
    void borrarCupon(String idCupon);
    boolean validarCupon(String idCupon, String userId);
    CuponDTO redimirCupon(String idCupon, String idCliente);


}
