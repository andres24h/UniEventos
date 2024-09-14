package co.edu.uniquindio.unieventos.dto.evento;

import co.edu.uniquindio.unieventos.documentos.Localidad;
import co.edu.uniquindio.unieventos.documentos.TipoEvento;

import java.util.List;

public record CrearEventoDTO(
        String idUsuario,
        String nombre,
        String descripcion,
        String direccion,
        String ciudad,
        String imagenPortada,
        String ImagenLocalidades,
        TipoEvento tipo,
        List<Localidad> localidades ) {
}
