package co.edu.uniquindio.unieventos.dto.cupon;

import co.edu.uniquindio.unieventos.documentos.TipoCupon;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ItemCuponDTO(
        String id,
        String codigo,
        String nombre,
        String estado,
        double descuento,
        LocalDateTime fechaVencimiento,
        String tipoCupon
        ){

}
