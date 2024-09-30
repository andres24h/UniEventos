package co.edu.uniquindio.unieventos.test;

import co.edu.uniquindio.unieventos.servicios.interfaces.CuentaServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuponServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CuponServicioTest {


    @Autowired
    private CuponServicio cuponServicio;
}
