package co.edu.uniquindio.unieventos.dto.cuenta;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record CrearCuentaDTO(
        @NotBlank @Length(max = 12) String cedula,
        @NotBlank @Length(max = 100) String nombre,
        @NotNull List<String> telefonos,
        @Length(max = 100) String direccion,
        @NotBlank @Length(max = 50) @Email String email,
        @NotBlank @Length(min = 7, max = 30) String password
) {
}