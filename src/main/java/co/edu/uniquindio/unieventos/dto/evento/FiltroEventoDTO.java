package co.edu.uniquindio.unieventos.dto.evento;

import co.edu.uniquindio.unieventos.documentos.TipoEvento;

public record FiltroEventoDTO(
        String nombre,
        TipoEvento tipo,
        String ciudad
) {
}