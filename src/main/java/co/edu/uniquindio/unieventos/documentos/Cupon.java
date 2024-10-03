package co.edu.uniquindio.unieventos.documentos;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    private double descuento;
    private EstadoCupon estado;
    private TipoCupon tipo;
    private LocalDateTime fechaVencimiento;
    private List<String> beneficiarios;

}
