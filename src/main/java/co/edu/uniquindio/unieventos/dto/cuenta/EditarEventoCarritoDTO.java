package co.edu.uniquindio.unieventos.dto.cuenta;

import org.bson.types.ObjectId;

public record EditarEventoCarritoDTO(
        String idCliente,
        ObjectId idDetalle,
        String nuevaLocalidad,
        int nuevaCantidad
) {
}
