package co.edu.uniquindio.unieventos.servicios.interfaces;

import co.edu.uniquindio.unieventos.dto.cupon.ActualizarCuponDTO;
import co.edu.uniquindio.unieventos.dto.cupon.CrearCuponDTO;
import co.edu.uniquindio.unieventos.dto.cupon.ItemCuponDTO;


public interface CuponServicio {
    String crearCupones(CrearCuponDTO cuponDTO);
    String actualizarCupon(ActualizarCuponDTO cuponDTO);
    void borrarCupon(String idCupon);
    boolean validarCupon(String idCupon, String userId);
    String redimirCupon(String idCupon, String idCliente);
    ItemCuponDTO listarCupones(String idCupon, String idCliente);


}
