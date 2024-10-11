package co.edu.uniquindio.unieventos.dto.evento;

import java.time.LocalDateTime;

public record FiltrarPorFechaDTO(
    LocalDateTime fechaInicio,
    LocalDateTime fechaFin
) {
}
