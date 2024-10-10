package co.edu.uniquindio.unieventos.dto.cupon;

import co.edu.uniquindio.unieventos.documentos.EstadoCupon;
import co.edu.uniquindio.unieventos.documentos.TipoCupon;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ActualizarCuponDTO(
        ObjectId id,
        String nombre,
        double descuento,
        LocalDateTime fechaVencimiento,
        TipoCupon tipoCupon,
        List<String> beneficiarios,
        EstadoCupon estadoCupon
) {
}
