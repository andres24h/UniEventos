package co.edu.uniquindio.unieventos.documentos;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Document("pagos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Pago implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;
    private String moneda;
    private String tipoPago;
    private String estado;
    private String detalleEstado;
    private String codigoAutorizacion;
    private float valorTransaccion;
    private LocalDateTime fecha;
}
