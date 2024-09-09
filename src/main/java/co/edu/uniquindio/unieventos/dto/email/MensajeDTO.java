package co.edu.uniquindio.unieventos.dto.email;

public record MensajeDTO<T>(
        boolean error,
        T respuesta
) {
}