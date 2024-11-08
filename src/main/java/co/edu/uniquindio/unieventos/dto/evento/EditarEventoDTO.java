package co.edu.uniquindio.unieventos.dto.evento;

import co.edu.uniquindio.unieventos.documentos.EstadoEvento;
import co.edu.uniquindio.unieventos.documentos.Localidad;
import co.edu.uniquindio.unieventos.documentos.TipoEvento;
import co.edu.uniquindio.unieventos.documentos.Ubicacion;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.validation.constraints.NotBlank;

public record EditarEventoDTO(
        @NotBlank String id,
        String nombre,
        String descripcion,
        String direccion,
        String imagenPortada,
        String imagenLocalidades,
        String tipo,
        List<Localidad> localidades,
        EstadoEvento estado,
        LocalDateTime fecha,
        Ubicacion ubicacion) {
}
