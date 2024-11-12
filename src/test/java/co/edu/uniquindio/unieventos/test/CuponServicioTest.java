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
                "CUPON-123456",
                "Navidad",
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
        ObjectId id=new ObjectId("6708786ca6dbd03a4bd252f8");
        List<String> beneficiarios=new ArrayList<>();
        ActualizarCuponDTO actualizarCuponDTO = new ActualizarCuponDTO(
                 id,
                "Navidad2",
                25.5,
                LocalDateTime.now().plusDays(20),
                beneficiarios,
                EstadoCupon.DISPONIBLE.toString()
                );

        assertDoesNotThrow(() -> {
            String updatedId = cuponServicio.actualizarCupon(actualizarCuponDTO);
            assertEquals("6708786ca6dbd03a4bd252f8", updatedId);
        });
    }

    @Test
    public void borrarCuponTest() throws Exception {

        assertDoesNotThrow(() -> {
            cuponServicio.borrarCupon("6708786ca6dbd03a4bd252f8");
            Cupon cupon = cuponRepo.findById("6708786ca6dbd03a4bd252f8").orElse(null);
            assertEquals(EstadoCupon.NO_DISPONIBLE,cupon.getEstado());
        });
    }

    @Test
    public void redimirCuponTest() {
        RedimirCuponDTO redimirCuponDTO = new RedimirCuponDTO("CUPON-140c128b-2141-42f1-b8c2-4377f6fb547f", "1010080936");

        assertDoesNotThrow(() -> {
            boolean resultado = cuponServicio.redimirCupon(redimirCuponDTO);
            assertTrue(resultado);
        });
    }

    @Test
    public void listarCuponesTest() throws Exception {

        List<ItemCuponDTO> cupones = cuponServicio.listarCupones();
        assertNotNull(cupones);
        assertFalse(cupones.isEmpty());

        assertEquals(2, cupones.size());
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



