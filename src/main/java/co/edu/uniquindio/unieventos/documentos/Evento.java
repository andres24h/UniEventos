package co.edu.uniquindio.unieventos.documentos;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Document("eventos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Evento {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String idUsuario;
    private String nombre;
    private String descripcion;
    private Ubicacion Ubicacion;
    private String direccion;
    private String ciudad;
    private String imagenPortada;
    private String ImagenLocalidades;
    private String estado;
    private String tipo;
    private LocalDateTime fecha;
    private List<Localidad> localidades;
}
