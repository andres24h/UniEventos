package co.edu.uniquindio.unieventos.dto.cuenta;

import jakarta.validation.constraints.NotBlank;

public record MensajeDTO<T>(
        @NotBlank boolean error,
        @NotBlank T respuesta
) {
}
