package co.edu.uniquindio.unieventos.dto.cuenta;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record InformacionCuentaDTO(
        @NotBlank String cedula,
        @NotBlank String nombre,
        List<String> telefonos,
        String direccion,
        @NotBlank @Email String correo


) {
}