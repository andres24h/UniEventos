package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.documentos.Usuario;
import org.apache.el.stream.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepo extends MongoRepository<Usuario, String> {

    Optional<Usuario> findById(String id);
}
