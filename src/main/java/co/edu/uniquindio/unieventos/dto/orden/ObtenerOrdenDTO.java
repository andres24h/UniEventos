package co.edu.uniquindio.unieventos.dto.orden;

import java.time.LocalDateTime;
import java.util.List;

public record ObtenerOrdenDTO(
        String id,
        String idCliente,
        String idCupon,
        LocalDateTime fecha,
        String codigoPasarela,
        double total,
        List<DetalleOrdenDTO> detalle
) {
}
