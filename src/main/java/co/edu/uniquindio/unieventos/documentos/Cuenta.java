package co.edu.uniquindio.unieventos.documentos;

import jakarta.annotation.PostConstruct;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString
public class Cuenta {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private Usuario usuario;
    private String email;
    private String password;
    private EstadoCuenta estado;
    private Rol rol;
    private CodigoValidacion codigoValidacionRegistro;
    private CodigoValidacion codigoValidacionPassword;
    private LocalDateTime fechaRegistro;



}
