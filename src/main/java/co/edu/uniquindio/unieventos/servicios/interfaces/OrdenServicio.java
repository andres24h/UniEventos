package co.edu.uniquindio.unieventos.servicios.interfaces;

import co.edu.uniquindio.unieventos.dto.orden.CrearOrdenDTO;
import co.edu.uniquindio.unieventos.dto.orden.OrdenDTO;
import com.mercadopago.resources.preference.Preference;

import java.util.List;
import java.util.Map;

public interface OrdenServicio {
    Preference realizarPago(String idOrden) throws Exception;
    void recibirNotificacionMercadoPago(Map<String, Object> request);
     String crearOrden(CrearOrdenDTO crearOrdenDTO);
    OrdenDTO obtenerOrden(String idOrden);
    List<OrdenDTO> listOrdenByUsuario(String idUsuario);
    void cancelarOrden(String idOrden);
    void procesarPago(String noclaro);
    String generarQRC(String idOrden);
}
