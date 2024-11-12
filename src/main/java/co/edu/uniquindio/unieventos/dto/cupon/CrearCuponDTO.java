package co.edu.uniquindio.unieventos.dto.cupon;

import co.edu.uniquindio.unieventos.documentos.EstadoCupon;
import co.edu.uniquindio.unieventos.documentos.TipoCupon;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.List;

public record CrearCuponDTO(
        String codigo,
        @NotBlank String nombre,
        @NotBlank String estado,
        @Positive double descuento,
        @NotNull LocalDateTime fechaVencimiento,
        @NotBlank String tipo,
        List<String> beneficiarios
) {
}
