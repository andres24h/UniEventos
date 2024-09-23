package co.edu.uniquindio.unieventos.dto.cuenta;

import java.util.List;

public record ItemCuentaDTO(
        String id,
        String nombre,
        String email,
        List<String > telefonos

) {
}
