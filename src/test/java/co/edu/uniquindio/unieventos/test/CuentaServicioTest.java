package co.edu.uniquindio.unieventos.test;

import co.edu.uniquindio.unieventos.documentos.Cuenta;
import co.edu.uniquindio.unieventos.documentos.EstadoCuenta;
import co.edu.uniquindio.unieventos.documentos.Rol;
import co.edu.uniquindio.unieventos.documentos.Usuario;
import co.edu.uniquindio.unieventos.dto.cuenta.*;
import co.edu.uniquindio.unieventos.repositorios.CuentaRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuentaServicio;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CuentaServicioTest {

    @Autowired
    private CuentaServicio cuentaServicio;

    @Autowired
    private CuentaRepo cuentaRepo;

    @Test
    public void crearAdministradoresTest() {

        Cuenta admin1 = new Cuenta();
        admin1.setId("Admin1");
        admin1.setRol(Rol.ADMINISTRADOR);
        admin1.setEstado(EstadoCuenta.ACTIVO);
        admin1.setEmail("admin1@domain.com");
        admin1.setPassword("password1");
        admin1.setFechaRegistro(LocalDateTime.now());
        admin1.setUsuario(new Usuario("Admin1", "Calle 1", Arrays.asList("1111111111")));


        Cuenta admin2 = new Cuenta();
        admin2.setId("Admin2");
        admin2.setRol(Rol.ADMINISTRADOR);
        admin2.setEstado(EstadoCuenta.ACTIVO);
        admin2.setEmail("admin2@domain.com");
        admin2.setPassword("password2");
        admin2.setFechaRegistro(LocalDateTime.now());
        admin2.setUsuario(new Usuario("Admin2", "Calle 2", Arrays.asList("2222222222")));


        Cuenta admin3 = new Cuenta();
        admin3.setId("Admin3");
        admin3.setRol(Rol.ADMINISTRADOR);
        admin3.setEstado(EstadoCuenta.ACTIVO);
        admin3.setEmail("admin3@domain.com");
        admin3.setPassword("password3");
        admin3.setFechaRegistro(LocalDateTime.now());
        admin3.setUsuario(new Usuario("Admin3", "Calle 3", Arrays.asList("3333333333")));


        cuentaRepo.save(admin1);
        cuentaRepo.save(admin2);
        cuentaRepo.save(admin3);


        assertNotNull(cuentaRepo.findById("Admin1"));
        assertNotNull(cuentaRepo.findById("Admin2"));
        assertNotNull(cuentaRepo.findById("Admin3"));
    }


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
        CodigoPasswordDTO codigoPasswordDTO=new CodigoPasswordDTO("luisc.moralesc@uqvirtual.edu.co");

        assertDoesNotThrow(() -> {
            String resultado = cuentaServicio.enviarCodigoRecuperacionPassword(codigoPasswordDTO);
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
                "VIP",
                new ObjectId("66fdb5885aa7fb5163c9584a"),
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
