package co.edu.uniquindio.unieventos.dto.cuenta;

public record EditarEventoCarritoDTO(
        String idCliente,
        org.bson.types.ObjectId idDetalle,
        String nuevaLocalidad,
        int nuevaCantidad
) {
}
