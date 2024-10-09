package co.edu.uniquindio.unieventos.dto.evento;

import co.edu.uniquindio.unieventos.documentos.EstadoEvento;
import co.edu.uniquindio.unieventos.documentos.Localidad;
import co.edu.uniquindio.unieventos.documentos.TipoEvento;
import co.edu.uniquindio.unieventos.documentos.Ubicacion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record CrearEventoDTO(
        @NotBlank String idUsuario,
        @NotBlank String nombre,
        @NotBlank String descripcion,
        @NotBlank String direccion,
        @NotBlank String ciudad,
        @NotBlank String imagenPortada,
        @NotBlank String imagenLocalidades,
        @NotNull TipoEvento tipo,
        @NotNull List<Localidad> localidades,
        @NotNull EstadoEvento estado,
        @NotNull LocalDateTime fecha,
        @NotNull Ubicacion ubicacion) {
}
