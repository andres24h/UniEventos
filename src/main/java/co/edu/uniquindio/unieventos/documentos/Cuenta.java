package co.edu.uniquindio.unieventos.documentos;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Document("cuentas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Cuenta implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private ObjectId idUsuario;
    private String email;
    private String password;
    private EstadoCuenta estado;
    private Rol rol;
    private CodigoValidacion codigoValidacionRegistro;
    private CodigoValidacion codigoValidacionPassword;
    private LocalDateTime fechaRegistro;

}
