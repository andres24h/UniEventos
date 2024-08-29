package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.documentos.DetalleCarrito;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DetalleCarritoRepo extends MongoRepository<DetalleCarrito, ObjectId> {
}
