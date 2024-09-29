package co.edu.uniquindio.unieventos.dto.cuenta;

import org.bson.types.ObjectId;

public record EliminarEventoDTO(
        String idCliente,
        ObjectId idDetalle
) {
}
