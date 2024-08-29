package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.documentos.Cupon;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CuponRepo extends MongoRepository<Cupon, String> {
}
