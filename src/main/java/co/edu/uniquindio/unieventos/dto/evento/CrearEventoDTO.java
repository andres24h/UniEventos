package co.edu.uniquindio.unieventos.dto.evento;

import co.edu.uniquindio.unieventos.documentos.EstadoEvento;
import co.edu.uniquindio.unieventos.documentos.Localidad;
import co.edu.uniquindio.unieventos.documentos.TipoEvento;
import co.edu.uniquindio.unieventos.documentos.Ubicacion;

import java.time.LocalDateTime;
import java.util.List;

public record CrearEventoDTO(
        String idUsuario,
        String nombre,
        String descripcion,
        String direccion,
        String ciudad,
        String imagenPortada,
        String imagenLocalidades,
        TipoEvento tipo,
        List<Localidad> localidades,
        EstadoEvento estado,
        LocalDateTime fecha,
        Ubicacion ubicacion
        ) {
}
