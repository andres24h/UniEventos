package co.edu.uniquindio.unieventos.dto.cuenta;

public record CambiarPasswordDTO(
        String email,
        String codigoVerificacion,
        String passwordNueva
) {
}