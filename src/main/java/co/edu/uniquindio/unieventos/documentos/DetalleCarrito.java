package co.edu.uniquindio.unieventos.documentos;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class DetalleCarrito  {
    @Id
    @EqualsAndHashCode.Include
    private ObjectId codigoDetalle;
    private ObjectId idEvento;
    private int cantidad;
    private String nombreLocalidad;
}
