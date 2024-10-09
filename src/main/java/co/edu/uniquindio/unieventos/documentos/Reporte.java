package co.edu.uniquindio.unieventos.documentos;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Reporte {
    private String id;
    private Evento evento;
    private LocalDateTime fechaGeneracion;
    private double porcentajeVenta;
    private List<Localidad> localidad;
    private double ganancias;
}
