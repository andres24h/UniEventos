package co.edu.uniquindio.unieventos.dto.orden;

import co.edu.uniquindio.unieventos.documentos.DetalleOrden;
import co.edu.uniquindio.unieventos.documentos.Pago;

import java.time.LocalDateTime;
import java.util.List;

public record ItemOrdenDTO (
        String idCliente,
        String idCupon,
        double total,
        LocalDateTime fecha,
        List<DetalleOrden>items
){
}
