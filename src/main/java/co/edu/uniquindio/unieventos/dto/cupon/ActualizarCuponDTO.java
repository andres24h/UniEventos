package co.edu.uniquindio.unieventos.dto.cupon;

import co.edu.uniquindio.unieventos.documentos.EstadoCupon;
import co.edu.uniquindio.unieventos.documentos.TipoCupon;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ActualizarCuponDTO(
        ObjectId id,
        String codigo,
        String nombre,
        double descuento,
        LocalDateTime fechaVencimiento,
        TipoCupon tipoCupon,
        EstadoCupon estadoCupon
) {
}
