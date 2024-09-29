package co.edu.uniquindio.unieventos.dto.cupon;

import co.edu.uniquindio.unieventos.documentos.TipoCupon;

import java.time.LocalDate;

public record ActualizarCuponDTO(
        String id,
        String codigo,
        String nombre,
        float descuento,
        LocalDate fechaVencimiento,
        TipoCupon tipoCupon,
        boolean activo
) {
}
