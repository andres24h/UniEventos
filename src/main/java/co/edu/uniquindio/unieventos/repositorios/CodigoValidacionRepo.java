package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.documentos.CodigoValidacion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CodigoValidacionRepo extends MongoRepository<CodigoValidacion, String> {
}
