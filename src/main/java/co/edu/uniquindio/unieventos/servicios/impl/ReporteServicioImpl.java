package co.edu.uniquindio.unieventos.servicios.impl;

import co.edu.uniquindio.unieventos.documentos.*;
import co.edu.uniquindio.unieventos.dto.reportes.GenerarReporteDTO;
import co.edu.uniquindio.unieventos.servicios.interfaces.ReporteServicio;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class ReporteServicioImpl implements ReporteServicio {
    public Reporte generarReporte(GenerarReporteDTO generarReporteDTO) {
        Evento evento = generarReporteDTO.evento();
        List<Orden> ordenes = generarReporteDTO.ordenes();


        Reporte reporte = new Reporte();
        reporte.setEvento(evento);
        reporte.setFechaGeneracion(LocalDateTime.now());

        double totalGanancias = 0;
        List<Localidad> localidadesReporte = new ArrayList<>();

        // Calculamos el porcentaje vendido por localidad
        for (Localidad localidad : evento.getLocalidades()) {
            int totalVendido = 0;

            // Revisamos todas las órdenes para sumar la cantidad vendida
            for (Orden orden : ordenes) {
                for (DetalleOrden detalle : orden.getItems()) {
                    if (detalle.getNombreLocalidad().equals(localidad.getNombre())) {
                        totalVendido += detalle.getCantidad();
                        totalGanancias += detalle.getCantidad() * detalle.getPrecioUnitario();
                    }
                }
            }

            double porcentajeVenta = (double) totalVendido / localidad.getCapacidadMaxima() * 100;
            localidad.setPorcentajeVenta(porcentajeVenta);
            localidadesReporte.add(localidad);
        }

        reporte.setLocalidad(localidadesReporte);
        reporte.setGanancias(totalGanancias);

        // Cálculo global de porcentaje de ventas en todas las localidades
        double porcentajeTotal = localidadesReporte.stream()
                .mapToDouble(Localidad::getPorcentajeVenta)
                .average()
                .orElse(0);

        reporte.setPorcentajeVenta(porcentajeTotal);

        return reporte;
    }

    // Método que genera el PDF a partir del reporte
    public void generarPDF(Reporte reporte, OutputStream outputStream) throws Exception {
        // Crear un PdfWriter
        PdfWriter writer = new PdfWriter(outputStream);

        // Crear un PdfDocument con el writer
        PdfDocument pdfDocument = new PdfDocument(writer);

        // Crear un documento con el PdfDocument
        Document document = new Document(pdfDocument);

        // Añadir contenido al PDF
        document.add(new Paragraph("Reporte de Evento: " + reporte.getEvento().getNombre()));
        document.add(new Paragraph("Fecha de Generación: " + reporte.getFechaGeneracion()));
        document.add(new Paragraph("Porcentaje de Venta Total: " + reporte.getPorcentajeVenta() + "%"));
        document.add(new Paragraph("Ganancias Totales: $" + reporte.getGanancias()));

        document.add(new Paragraph("Detalle por Localidad:"));
        for (Localidad localidad : reporte.getLocalidad()) {
            document.add(new Paragraph(localidad.getNombre() + ": " + localidad.getPorcentajeVenta() + "% vendido"));
        }

        // Cerrar el documento
        document.close();
    }

}
