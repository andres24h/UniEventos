package co.edu.uniquindio.unieventos.servicios.interfaces;


import co.edu.uniquindio.unieventos.dto.email.EmailDTO;

public interface EmailServicio {
    boolean enviarCorreo(EmailDTO emailDTO) throws Exception;
}