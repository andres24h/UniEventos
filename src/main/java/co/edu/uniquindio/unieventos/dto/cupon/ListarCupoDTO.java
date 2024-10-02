package co.edu.uniquindio.unieventos.dto.cupon;

import jakarta.validation.constraints.NotBlank;

public record ListarCupoDTO(
        @NotBlank String idCliente
) {
}
