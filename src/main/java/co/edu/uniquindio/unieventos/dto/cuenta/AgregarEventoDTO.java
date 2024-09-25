package co.edu.uniquindio.unieventos.dto.cuenta;

import co.edu.uniquindio.unieventos.documentos.DetalleCarrito;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

public record AgregarEventoDTO(
        int cantidad,
        String nombreLocalidad,
        String idEvento,
        String idUsuario,
        LocalDateTime fecha){
}
