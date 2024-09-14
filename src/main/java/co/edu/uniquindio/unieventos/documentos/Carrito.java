package co.edu.uniquindio.unieventos.documentos;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Document("carrito")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString

public class Carrito implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String idUsuario;
    private LocalDateTime fecha;
    private List<DetalleCarrito> items;
}
