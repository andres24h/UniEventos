package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.documentos.DetalleOrden;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DetalleOrdenRepo extends MongoRepository<DetalleOrden, String> {
}
