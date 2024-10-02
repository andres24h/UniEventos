package co.edu.uniquindio.unieventos.dto.orden;

import co.edu.uniquindio.unieventos.documentos.DetalleOrden;
import co.edu.uniquindio.unieventos.documentos.Pago;
import org.bson.types.ObjectId;

import java.util.List;

public record CrearOrdenDTO(
     String idCliente,
     ObjectId idCupon
) {
}
