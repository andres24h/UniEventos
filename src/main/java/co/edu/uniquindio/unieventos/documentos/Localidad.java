package co.edu.uniquindio.unieventos.documentos;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Localidad {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String nombre;
    private double precio;
    private int entradasVendidas;
    private int capacidadMaxima;
    private double porcentajeVenta;

    public int cantidadDisponible() {
        return capacidadMaxima - entradasVendidas;
    }

}
