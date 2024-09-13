package co.edu.uniquindio.unieventos.test;

import co.edu.uniquindio.unieventos.dto.cuenta.CrearCuentaDTO;
import co.edu.uniquindio.unieventos.dto.cuenta.EditarCuentaDTO;
import co.edu.uniquindio.unieventos.dto.cuenta.InformacionCuentaDTO;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuentaServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CuentaServicioTest {

    @Autowired
    private CuentaServicio cuentaServicio;


    @Test
    public void crearCuentaTest(){
        // Crear un DTO con los datos para crear una nueva cuenta
        List<String> telefonos=new ArrayList<>();
        telefonos.add("315252671");
        telefonos.add("7432124");
        CrearCuentaDTO crearCuentaDTO = new CrearCuentaDTO(
                "123",
                "Pepito perez",
                telefonos,
                "Calle 123",
                "pepitoperez@email.com",
                "password"
            );
            // Se espera que no se lance ninguna excepción
            assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = cuentaServicio.crearCuenta(crearCuentaDTO);
            // Se espera que el id no sea nulo
            assertNotNull(id);

        });
    }
    @Test
    public void editarCuentaTest(){


        //Se define el id de la cuenta del usuario a actualizar, este id está en el dataset.js
        String idCuenta = "66a2a9aaa8620e3c1c5437be";
        //Se crea un objeto de tipo EditarCuentaDTO
        List<String> telefonos=new ArrayList<>();
        telefonos.add("3204922");
        EditarCuentaDTO editarCuentaDTO = new EditarCuentaDTO(
                idCuenta,
                "Pepito perez",
                telefonos,
                "Nueva dirección",
                "password"
        );


        //Se espera que no se lance ninguna excepción
        assertDoesNotThrow(() -> {
            //Se actualiza la cuenta del usuario con el id definido
            cuentaServicio.editarCuenta(editarCuentaDTO);


            //Obtenemos el detalle de la cuenta del usuario con el id definido
            InformacionCuentaDTO detalle = cuentaServicio.obtenerInformacionCuenta(idCuenta);


            //Se verifica que la dirección del usuario sea la actualizada
            assertEquals("Nueva dirección", detalle.direccion());
        });
    }


    @Test
    public void eliminarCuentaTest(){
        //Se define el id de la cuenta del usuario a eliminar, este id está en el dataset.js
        String idCuenta = "66a2a9aaa8620e3c1c5437be";

        //Se elimina la cuenta del usuario con el id definido
        assertDoesNotThrow(() -> cuentaServicio.eliminarCuenta(idCuenta) );


        //Al intentar obtener la cuenta del usuario con el id definido se debe lanzar una excepción
        assertThrows(Exception.class, () -> cuentaServicio.obtenerInformacionCuenta(idCuenta) );
    }

}
