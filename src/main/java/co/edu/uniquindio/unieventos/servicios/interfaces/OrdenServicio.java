package co.edu.uniquindio.unieventos.servicios.interfaces;

import co.edu.uniquindio.unieventos.documentos.Orden;
import co.edu.uniquindio.unieventos.dto.orden.CrearOrdenDTO;
import co.edu.uniquindio.unieventos.dto.orden.ItemOrdenDTO;
import com.mercadopago.resources.preference.Preference;

import java.util.List;
import java.util.Map;

public interface OrdenServicio {
    Preference realizarPago(String idOrden) throws Exception;
    void recibirNotificacionMercadoPago(Map<String, Object> request);
    String crearOrden(CrearOrdenDTO crearOrdenDTO)throws Exception;
    Orden obtenerOrden(String idOrden)throws Exception;
    List<ItemOrdenDTO> listOrdenByEvento(String idEvento);
    List<ItemOrdenDTO> listOrdenByUsuario(String idUsuario);


}
