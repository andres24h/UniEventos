package co.edu.uniquindio.unieventos.dto.cuenta;

import jakarta.validation.constraints.NotBlank;
import org.bson.types.ObjectId;

public record EliminarEventoDTO(
        @NotBlank String idCliente,
        @NotBlank ObjectId idDetalle
) {
}
