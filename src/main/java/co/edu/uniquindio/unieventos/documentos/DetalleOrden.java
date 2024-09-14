package co.edu.uniquindio.unieventos.documentos;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class DetalleOrden implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private ObjectId idEvento;
    private float precio;
    private String nombreLocalidad;
}
