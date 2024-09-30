package co.edu.uniquindio.unieventos.dto.cupon;

import co.edu.uniquindio.unieventos.documentos.Cuenta;
import co.edu.uniquindio.unieventos.documentos.EstadoCupon;
import co.edu.uniquindio.unieventos.documentos.TipoCupon;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record CrearCuponDTO(
        @NotBlank String codigo,
        @NotBlank String nombre,
        @NotNull float descuento,
        @Future LocalDateTime fechaVencimiento,
        @NotNull TipoCupon tipo,
        @NotBlank EstadoCupon estado,
        List<Cuenta> beneficiarios
) {
}
