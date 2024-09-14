package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.documentos.Evento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface EventoRepo extends MongoRepository<Evento, String> {
    @Query("{tipo: ?0}")
    Optional<Evento> findByTipo(String tipo);

    Optional<Evento> findById(String id);

    Optional<Evento> findByNombre(String nombre);
}
