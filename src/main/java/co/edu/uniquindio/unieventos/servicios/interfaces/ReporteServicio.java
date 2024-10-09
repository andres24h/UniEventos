package co.edu.uniquindio.unieventos.servicios.interfaces;

import co.edu.uniquindio.unieventos.documentos.Reporte;
import co.edu.uniquindio.unieventos.dto.reportes.GenerarReporteDTO;

import java.io.OutputStream;

public interface ReporteServicio {

    Reporte generarReporte(GenerarReporteDTO generarReporteDTO) throws Exception;
    void generarPDF(Reporte reporte, OutputStream outputStream) throws Exception;
}
