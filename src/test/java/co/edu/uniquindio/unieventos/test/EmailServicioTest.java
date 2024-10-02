package co.edu.uniquindio.unieventos.test;

import co.edu.uniquindio.unieventos.dto.email.EmailDTO;
import co.edu.uniquindio.unieventos.servicios.interfaces.EmailServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServicioTest {

     @Autowired
     EmailServicio emailServicio;

     @Test
     public void enviarCorreoTest() throws Exception {

          EmailDTO emailDTO = new EmailDTO("Prueba de envío", "Mensaje de prueba 4 ", "amhernandezp@uqvirtual.edu.co");

          boolean enviado = emailServicio.enviarCorreo(emailDTO);
          Assertions.assertTrue(enviado, "El correo no fue enviado correctamente");
     }
}
