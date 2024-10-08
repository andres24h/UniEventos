package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.documentos.Cuenta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CuentaRepo extends MongoRepository<Cuenta, String> {
    @Query("{email:  ?0}")
    Optional<Cuenta> buscarEmail(String correo);

    Optional<Cuenta> findById(String id);


    @Query("{id: ?0}")
    Optional<Cuenta> buscarId(String id);

    List<Cuenta> findByRol(String rol);


    @Query("{email:  ?0, password:  ?1}")
    Optional<Cuenta> validarDatosAutentificacion(String email, String password);

}
