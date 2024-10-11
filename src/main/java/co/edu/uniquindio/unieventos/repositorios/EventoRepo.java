package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.documentos.Evento;
import co.edu.uniquindio.unieventos.documentos.TipoEvento;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventoRepo extends MongoRepository<Evento, String> {
    @Query("{tipo: ?0}")
    Optional<Evento> findByTipo(TipoEvento tipo);

    Optional<Evento> findById(String id);
    Optional<Evento> findById(ObjectId id);

    Optional<Evento> findByCiudad(String ciudad);
    Optional<Evento> findByNombreContains(String nombre);
    Optional<Evento> findByNombre(String nombre);
    Optional<Evento> findByDireccionContains(String direccion);
    Optional<Evento> findByNombreAndCiudad(String nombre, String ciudad);
    Optional<Evento> findByFechaBetween(LocalDateTime fecha, LocalDateTime fecha2);
    Optional<Evento> findByFechaBetweenAndCiudad(LocalDateTime fechaInicio, LocalDateTime fechaFin, String ciudad);
    Optional<Evento> findByFechaBetweenAndTipo(LocalDateTime fechaInicio, LocalDateTime fechaFin, TipoEvento tipo);
    Optional<Evento> findByFechaBetweenAndTipoAndCiudad(LocalDateTime fechaInicio, LocalDateTime fechaFin, TipoEvento tipo, String ciudad);
    Optional<Evento> findByTipoAndCiudad(TipoEvento tipo, String ciudad);
    List<Evento> findAll();
}
