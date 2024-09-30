package co.edu.uniquindio.unieventos.dto.cuenta;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ItemCuentaDTO(
        @NotBlank String id,
        @NotBlank String nombre,
        @NotBlank String email,
        List<String > telefonos

) {
}
