package co.edu.uniquindio.unieventos.dto.cuenta;

public record EditarEventoCarritoDTO(
        String idCliente,
        String idDetalle,
        String nuevaLocalidad,
        int nuevaCantidad
) {
}
