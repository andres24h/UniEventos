package co.edu.uniquindio.unieventos.dto.cuenta;

import co.edu.uniquindio.unieventos.documentos.DetalleCarrito;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

public record AgregarEventoDTO(
        @NotNull int cantidad,
        @NotBlank String nombreLocalidad,
        @NotNull ObjectId idEvento,
        @NotBlank String idUsuario,
        @NotNull LocalDateTime fecha){
}
