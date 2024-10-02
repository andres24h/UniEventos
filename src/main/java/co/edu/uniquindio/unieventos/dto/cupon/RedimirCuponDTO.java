package co.edu.uniquindio.unieventos.dto.cupon;

import org.hibernate.validator.constraints.NotBlank;

public record RedimirCuponDTO(
        @NotBlank String codigoCupon,
        @NotBlank String idCliente
) {
}
