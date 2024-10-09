package co.edu.uniquindio.unieventos.dto.cuenta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;

public record EliminarEventoDTO(
        @NotBlank String idCliente,
        @NotNull ObjectId idDetalle
) {
}
