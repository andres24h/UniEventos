package co.edu.uniquindio.unieventos.documentos;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class CodigoValidacion implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    private LocalDateTime fechaCreacion;
}
