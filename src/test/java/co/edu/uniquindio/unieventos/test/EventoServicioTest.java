package co.edu.uniquindio.unieventos.test;

import co.edu.uniquindio.unieventos.servicios.interfaces.EventoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EventoServicioTest {

    @Autowired
    private EventoServicio eventoServicio;
}
