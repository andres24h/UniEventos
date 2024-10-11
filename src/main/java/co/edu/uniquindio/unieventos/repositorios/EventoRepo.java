package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.documentos.Evento;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EventoRepo extends MongoRepository<Evento, String> {
    @Query("{tipo: ?0}")
    Optional<Evento> findByTipo(String tipo);

    Optional<Evento> findById(String id);
    Optional<Evento> findById(ObjectId id);

    Optional<Evento> findByCiudad(String ciudad);
    Optional<Evento> findByNombreContains(String nombre);
    Optional<Evento> findByNombre(String nombre);
    Optional<Evento> findByDescripcionContains(String descripcion);
    Optional<Evento> findByDireccionContains(String direccion);
    Optional<Evento> findByNombreAndCiudad(String nombre, String ciudad);
    Optional<Evento> findByFechaBetween(LocalDateTime fecha, LocalDateTime fecha2);
    List<Evento> findAll();
}
