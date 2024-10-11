package co.edu.uniquindio.unieventos.dto.evento;

import co.edu.uniquindio.unieventos.documentos.TipoEvento;

import java.time.LocalDateTime;

public record FiltrarPorFechaYTipoDTO(
    LocalDateTime fechaInicio,
    LocalDateTime fechaFin,
    TipoEvento tipo
) {
}
