package co.edu.uniquindio.unieventos.servicios.impl;

import co.edu.uniquindio.unieventos.dto.email.EmailDTO;
import co.edu.uniquindio.unieventos.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.unieventos.config.JWTUtils;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServicioImpl implements EmailServicio {

    @Autowired
    private JWTUtils jwtUtils;

    private final JavaMailSender javaMailSender;

    /**
     * envio de correo normal puede servir para notificaciones
     *
     * @param emailDTO
     * @throws Exception
     */
    @Override
    public boolean enviarCorreo(EmailDTO emailDTO) throws Exception {

        MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje);

        try {

            helper.setSubject(emailDTO.asunto());
            helper.setText(emailDTO.cuerpo(), true);
            helper.setTo(emailDTO.destinatario());
            helper.setFrom("no_reply@dominio.com");

            javaMailSender.send(mensaje);

            return true; // Retorna true si el correo se envía correctamente

        } catch (Exception e) {
            e.printStackTrace();

            return false; // Retornar false si ocurre algún error
        }
    }
}
