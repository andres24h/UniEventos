package co.edu.uniquindio.unieventos.test;

import co.edu.uniquindio.unieventos.dto.cuenta.*;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuentaServicio;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CuentaServicioTest {

    @Autowired
    private CuentaServicio cuentaServicio;


    @Test
    public void crearCuentaTest(){

        List<String> telefonos=new ArrayList<>();
        telefonos.add("315252671");
        telefonos.add("743212426");
        CrearCuentaDTO crearCuentaDTO = new CrearCuentaDTO(
                "3",
                "Cuenta 3",
                telefonos,
                "Calle 12 #26-49",
                "cuenta3@gmail.com",
                "password123"
            );

            assertDoesNotThrow( () -> {

            String id = cuentaServicio.crearCuenta(crearCuentaDTO);

            assertNotNull(id);

        });
    }
    @Test
    public void activarCuentaTest() {
        ActivarCuentaDTO activarCuentaDTO = new ActivarCuentaDTO(
                "luisc.moralesc@uqvirtual.edu.co",
                "ATFK1uaziy"
        );


        assertDoesNotThrow(() -> {
            boolean resultado = cuentaServicio.activarCuenta(activarCuentaDTO);
            assertTrue(resultado);
        });
    }

    @Test
    public void editarCuentaTest(){
        String idCuenta = "1091884230";
        List<String> telefonos=new ArrayList<>();
        telefonos.add("320492232");
        EditarCuentaDTO editarCuentaDTO = new EditarCuentaDTO(
                idCuenta,
                "Carlos Morales",
                telefonos,
                "Calle 50 #30-42"
        );
        assertDoesNotThrow(() -> {
            cuentaServicio.editarCuenta(editarCuentaDTO);
            InformacionCuentaDTO detalle = cuentaServicio.obtenerInformacionCuenta(idCuenta);
            assertEquals("Calle 50 #30-42", detalle.direccion());
        });
    }


    @Test
    public void eliminarCuentaTest()  {
        String idCuenta = "1091884230";
        assertDoesNotThrow(() -> cuentaServicio.eliminarCuenta(idCuenta) );

    }

    @Test
    public void enviarCodigoRecuperacionPasswordTest() {
        String correo = "luisc.moralesc@uqvirtual.edu.co";

        assertDoesNotThrow(() -> {
            String resultado = cuentaServicio.enviarCodigoRecuperacionPassword(correo);
            assertEquals("se ha enviado un correo de validacion", resultado);  // Verifica que el mensaje sea el esperado
        });
    }
    @Test
    public void cambiarPasswordTest() {
        CambiarPasswordDTO cambiarPasswordDTO = new CambiarPasswordDTO(
                "luisc.moralesc@uqvirtual.edu.co",
                "Hr7yyupmBn", //Codigo que recibimos por correo
                "nuevaPassword"
        );

        assertDoesNotThrow(() -> {
            String resultado = cuentaServicio.cambiarPassword(cambiarPasswordDTO);
            assertEquals("contraseña cambiada correctamente",resultado);  // Verifica que la contraseña se cambie correctamente
        });
    }
    @Test
    public void iniciarSesionTest() {
        LoginDTO loginDTO = new LoginDTO(
                "luisc.moralesc@uqvirtual.edu.co",
                "nuevaPassword"
        );

        assertDoesNotThrow(() -> {
            TokenDTO tokenDTO=cuentaServicio.iniciarSesion(loginDTO);
            assertEquals("Token", tokenDTO.toString());
        });
    }


    @Test
    public void eliminarEventoCarritoTest() {
        EliminarEventoDTO eliminarEventoDTO = new EliminarEventoDTO(
                "1091884230",
                new ObjectId("66f8a476e6f00716b3b59ece")
        );

        assertDoesNotThrow(() -> {
            String resultado = cuentaServicio.eliminarEventoCarrito(eliminarEventoDTO);
            assertEquals("Evento eliminado del carrito correctamente", resultado);  // Verifica que el evento se elimine correctamente
        });
    }
    @Test
    public void agregarEventoCarritoTest() {
        AgregarEventoDTO agregarEventoDTO = new AgregarEventoDTO(
                3,
                "General",
                "evento123",
                "1091884230",
                LocalDateTime.now()
        );

        assertDoesNotThrow(() -> {
            String resultado = cuentaServicio.agregarEventoCarrito(agregarEventoDTO);
            assertEquals("Evento agregado al carrito con éxito", resultado);  // Verifica que el evento se agregue correctamente
        });
    }

    @Test
    public void listarTest(){

        List<ItemCuentaDTO> lista = cuentaServicio.listarCuentas();

        assertEquals(3, lista.size());
    }

}
