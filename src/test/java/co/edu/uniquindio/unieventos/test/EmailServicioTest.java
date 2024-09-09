package co.edu.uniquindio.unieventos.test;

import co.edu.uniquindio.unieventos.dto.email.EmailDTO;
import co.edu.uniquindio.unieventos.servicios.interfaces.EmailServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServicioTest {

     @Autowired
     EmailServicio emailServicio;

     @Test
     public void enviarEmailTest() throws Exception {
         emailServicio.enviarCorreo(new EmailDTO("Asunto", "Cuerpo mensaje", "luisc.moralesc@uqvirtual.edu.co"));
     }
}
