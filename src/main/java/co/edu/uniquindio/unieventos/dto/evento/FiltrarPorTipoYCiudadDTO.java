package co.edu.uniquindio.unieventos.dto.evento;

import co.edu.uniquindio.unieventos.documentos.TipoEvento;

public record FiltrarPorTipoYCiudadDTO(
    TipoEvento tipo,
    String ciudad
) {
}
