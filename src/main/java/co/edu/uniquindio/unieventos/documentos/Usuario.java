package co.edu.uniquindio.unieventos.documentos;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document("usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Usuario  implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String nombre;
    private String cedula;
    private String direccion;
    private List<String> telefonos;

    @Builder
    public Usuario(String nombre, String cedula, String direccion, List<String> telefonos) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.direccion = direccion;
        this.telefonos = telefonos;
    }
}
