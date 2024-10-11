package co.edu.uniquindio.unieventos.dto.evento;

import java.time.LocalDateTime;

public record FiltrarPorFechaYCiudadDTO(
    LocalDateTime fechaInicio,
    LocalDateTime fechaFin,
    String ciudad
) {
}
