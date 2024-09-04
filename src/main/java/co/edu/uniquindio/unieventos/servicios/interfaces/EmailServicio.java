package co.edu.uniquindio.unieventos.servicios.interfaces;


import co.edu.uniquindio.unieventos.dto.email.EmailDTO;

public interface EmailServicio {
    void enviarCorreo(EmailDTO emailDTO) throws Exception;
}