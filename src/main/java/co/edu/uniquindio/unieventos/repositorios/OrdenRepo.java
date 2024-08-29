package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.documentos.Orden;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrdenRepo extends MongoRepository<Orden, String> {
}
