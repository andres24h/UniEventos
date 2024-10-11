package co.edu.uniquindio.unieventos.test;

import co.edu.uniquindio.unieventos.documentos.Cupon;
import co.edu.uniquindio.unieventos.documentos.EstadoCupon;
import co.edu.uniquindio.unieventos.documentos.TipoCupon;
import co.edu.uniquindio.unieventos.dto.cuenta.CrearCuentaDTO;
import co.edu.uniquindio.unieventos.dto.cupon.*;
import co.edu.uniquindio.unieventos.repositorios.CuponRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuentaServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuponServicio;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CuponServicioTest {

    @Autowired
    private CuponServicio cuponServicio;
    @Autowired
    private CuponRepo cuponRepo;

    @Test
    public void crearCuponUnicoTest() {
        CrearCuponDTO crearCuponDTO = new CrearCuponDTO(
                "CUPON-12345",
                "Hallowen",
                20,
                LocalDateTime.now().plusDays(5),
                TipoCupon.UNICO,
                null //Es para todos los usuarios
        );

        assertDoesNotThrow(() -> {
            String id = cuponServicio.crearCupones(crearCuponDTO);
            assertNotNull(id);
        });
    }
    @Test
    public void crearCuponIndividualTest() {
        List<String> beneficiarios=new ArrayList<>();
        //Estos son id de clientes que
        //estan en la base de datos
        beneficiarios.add("1");
        beneficiarios.add("2");

        CrearCuponDTO crearCuponDTO = new CrearCuponDTO(
                "CUPON-12345",
                "Hallowen",
                20,
                LocalDateTime.now().plusDays(5),
                TipoCupon.INDIVIDUAL,
                beneficiarios
        );

        assertDoesNotThrow(() -> {
            String id = cuponServicio.crearCupones(crearCuponDTO);
            assertNotNull(id);
        });
    }
    @Test
    public void actualizarCuponTest() throws Exception {
        ObjectId id=new ObjectId("66fd8b358f12651396981a20");
        List<String> beneficiarios=new ArrayList<>();
        ActualizarCuponDTO actualizarCuponDTO = new ActualizarCuponDTO(
                 id,
                "CUPON-1234567F8",
                25.5,
                LocalDateTime.now().plusDays(20),
                beneficiarios,
                EstadoCupon.DISPONIBLE
                );

        assertDoesNotThrow(() -> {
            String updatedId = cuponServicio.actualizarCupon(actualizarCuponDTO);
            assertEquals("66fd8b358f12651396981a20", updatedId);
        });
    }

    @Test
    public void borrarCuponTest() throws Exception {

        assertDoesNotThrow(() -> {
            cuponServicio.borrarCupon("");
            Optional<Cupon> deletedCupon = cuponRepo.findById("");
            assertTrue(deletedCupon.isEmpty());
        });
    }

    @Test
    public void redimirCuponTest() {
        RedimirCuponDTO redimirCuponDTO = new RedimirCuponDTO("CUPON-AA12", "1");

        assertDoesNotThrow(() -> {
            boolean resultado = cuponServicio.redimirCupon(redimirCuponDTO);
            assertEquals(true, resultado);
        });
    }

    @Test
    public void listarCuponesTest() throws Exception {

        List<ItemCuponDTO> cupones = cuponServicio.listarCupones();
        assertNotNull(cupones);
        assertFalse(cupones.isEmpty());

        assertEquals(3, cupones.size());
    }

    @Test
    public void listarCuponesClienteTest() throws Exception {
        ListarCupoDTO listarCupoDTO = new ListarCupoDTO("1");
        List<ItemCuponDTO> cuponesCliente = cuponServicio.listarCuponesCliente(listarCupoDTO);

        assertNotNull(cuponesCliente);
        assertFalse(cuponesCliente.isEmpty());
        assertEquals(1, cuponesCliente.size());

    }
}



