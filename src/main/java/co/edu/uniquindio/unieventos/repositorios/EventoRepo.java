package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.documentos.Evento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventoRepo extends MongoRepository<Evento, String> {
}
