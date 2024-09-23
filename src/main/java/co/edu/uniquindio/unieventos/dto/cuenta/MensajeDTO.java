package co.edu.uniquindio.unieventos.dto.cuenta;

public record MensajeDTO<T>(
        boolean error,
        T respuesta
) {
}
