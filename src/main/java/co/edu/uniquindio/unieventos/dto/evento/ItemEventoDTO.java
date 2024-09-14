package co.edu.uniquindio.unieventos.dto.evento;

import java.time.LocalDateTime;

public record ItemEventoDTO(
        String id,
        String nombre,
        String descripcion,
        String urlImagenPoster,
        LocalDateTime fecha,
        String direccion
) {
}