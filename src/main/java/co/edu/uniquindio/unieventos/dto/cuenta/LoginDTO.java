package co.edu.uniquindio.unieventos.dto.cuenta;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank String correo,
        @NotBlank String password
) {
}