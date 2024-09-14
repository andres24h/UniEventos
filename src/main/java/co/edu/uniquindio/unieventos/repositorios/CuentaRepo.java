package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.documentos.Cuenta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface CuentaRepo extends MongoRepository<Cuenta, String> {
    @Query("{email:  ?0}")
    Optional<Cuenta> buscarEmail(String correo);

    @Query("{id: ?0}")
    Optional<Cuenta> buscarId(String id);

    @Query("{email:  ?0, password:  ?1}")
    Optional<Cuenta> validarDatosAutentificacion(String email, String password);
}
