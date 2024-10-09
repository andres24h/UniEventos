package co.edu.uniquindio.unieventos.dto.reportes;

import co.edu.uniquindio.unieventos.documentos.Evento;
import co.edu.uniquindio.unieventos.documentos.Orden;

import java.util.List;

public record GenerarReporteDTO(
        Evento evento,
        List<Orden> ordenes
) {
}
