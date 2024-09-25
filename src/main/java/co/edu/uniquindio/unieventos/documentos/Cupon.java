package co.edu.uniquindio.unieventos.documentos;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Document("cupones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Cupon implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String nombre;
    private String codigo;
    private float descuento;
    private EstadoCupon estado;
    private TipoCupon tipo;
    private LocalDateTime fechaVencimiento;


    @Builder
    public Cupon( String nombre, String codigo, float descuento,
                 TipoCupon tipo, LocalDateTime fechaVencimiento) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.descuento = descuento;
        this.estado = EstadoCupon.NO_DISPONIBLE;
        this.tipo = tipo;
        this.fechaVencimiento = fechaVencimiento;
    }

    public Cupon(Object o, String nombre, String codigo, float descuento, TipoCupon tipo, LocalDate localDate) {
    }


    public Cupon(Object o, String nombre, String codigo, float descuento, TipoCupon tipo, LocalDateTime localDateTime) {
    }
}
