package co.edu.uniquindio.unieventos.dto.cuenta;

import jakarta.validation.constraints.NotBlank;

public record TokenDTO (
        @NotBlank String token
) {
}