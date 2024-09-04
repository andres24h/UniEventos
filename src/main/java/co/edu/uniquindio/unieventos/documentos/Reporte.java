package co.edu.uniquindio.unieventos.documentos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Reporte {
    private Evento evento;
    private double porcentajeVenta;
    private Localidad localidad;
    private double ganancias;
}
