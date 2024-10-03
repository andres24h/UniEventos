package co.edu.uniquindio.unieventos.test;

import co.edu.uniquindio.unieventos.documentos.Orden;
import co.edu.uniquindio.unieventos.dto.orden.CrearOrdenDTO;
import co.edu.uniquindio.unieventos.repositorios.CuentaRepo;
import co.edu.uniquindio.unieventos.repositorios.EventoRepo;
import co.edu.uniquindio.unieventos.repositorios.OrdenRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.OrdenServicio;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrdenServicioTest {

    @Autowired
    OrdenServicio ordenServicio;

    @Autowired
    OrdenRepo ordenRepo;


    @Test
    public void crearOrdenConCuponRedimidoTest() {
        assertDoesNotThrow(() -> {

            CrearOrdenDTO crearOrdenDTO = new CrearOrdenDTO("1091884230","CUPON-AA12");

            String idOrden = ordenServicio.crearOrden(crearOrdenDTO);

            assertNotNull(idOrden);

            Orden orden = ordenRepo.findById(idOrden).orElse(null);
            assertNotNull(orden);
            assertEquals(1, orden.getItems().size());
            assertEquals(180, orden.getTotal());
        });
    }

    @Test
    public void crearOrdenSinCuponTest() {
        assertDoesNotThrow(() -> {

            CrearOrdenDTO crearOrdenDTO = new CrearOrdenDTO("cliente123", null);

            String idOrden = ordenServicio.crearOrden(crearOrdenDTO);

            assertNotNull(idOrden);


            Orden orden = ordenRepo.findById(idOrden).orElse(null);
            assertNotNull(orden);
            assertEquals(1, orden.getItems().size());
            assertEquals(200, orden.getTotal()); // 2 entradas a $100 c/u sin descuento
        });
    }
}

