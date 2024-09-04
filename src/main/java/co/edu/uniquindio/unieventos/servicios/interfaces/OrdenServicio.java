package co.edu.uniquindio.unieventos.servicios.interfaces;

import co.edu.uniquindio.unieventos.dto.orden.OrdenDTO;

import java.util.List;

public interface OrdenServicio {

    OrdenDTO crearOrden(OrdenDTO Order);
    OrdenDTO obtenerOrden(String idOrden);
    List<OrdenDTO> listOrdenByUsuario(String idUsuario);
    void cancelarOrden(String idOrden);
    void procesarPago(String noclaro);
    String generarQRC(String idOrden);
}
