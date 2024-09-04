package co.edu.uniquindio.unieventos.dto.email;

import jakarta.validation.constraints.NotBlank;

public record EmailDTO(
        @NotBlank String asunto,
        String cuerpo,
        @NotBlank String destinatario) {
}