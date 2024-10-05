package co.edu.uniquindio.unieventos.dto.global;

public record MensajeDTO<T>(
        boolean error,
        T respuesta
) {
}