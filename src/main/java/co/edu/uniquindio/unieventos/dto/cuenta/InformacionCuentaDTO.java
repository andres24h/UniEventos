package co.edu.uniquindio.unieventos.dto.cuenta;

import java.util.List;

public record InformacionCuentaDTO(
        String cedula,
        String nombre,
        List<String> telefonos,
        String direccion,
        String correo
) {
}