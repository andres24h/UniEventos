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
public class Evento implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String nombre;
    private String descripcion;
    private String direccion;
    private String ciudad;
    private String imagenPortada;
    private String ImagenLocalidades;
    private EstadoEvento estado;
    private TipoEvento tipo;
    private LocalDateTime fecha;
    private List<Localidad> localidades;

}
