package co.edu.uniquindio.unieventos.dto.cupon;

import co.edu.uniquindio.unieventos.documentos.TipoCupon;

import java.time.LocalDate;

public record ItemCuponDTO(
        String id,
        String codigo,
        String nombre,
        double porcentajeDescuento,
        LocalDate expiracion,
        TipoCupon tipoCupon,
        boolean isActivo,
        String userId
)
 {
}
