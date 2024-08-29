package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.documentos.Carrito;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarritoRepo extends MongoRepository<Carrito, String> {
}
