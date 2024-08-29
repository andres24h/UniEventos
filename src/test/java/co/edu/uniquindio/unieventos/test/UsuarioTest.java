package co.edu.uniquindio.unieventos.test;

import co.edu.uniquindio.unieventos.documentos.Usuario;
import co.edu.uniquindio.unieventos.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UsuarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Test
    public void crear() {

        // Se crea el usuario con sus propiedades
        Usuario usuario = Usuario.builder()
                .nombre("luis")
                .cedula("67890")
                .direccion("Manizales")
                .telefonos(List.of("3111234567", "74098765"))
                .build();

        // Se guarda el cliente
        Usuario guardado = usuarioRepo.save(usuario);

        // Se verifica que el valor actual sea el mismo que el valor esperado
        assertEquals("luis", guardado.getNombre());
    }

}
