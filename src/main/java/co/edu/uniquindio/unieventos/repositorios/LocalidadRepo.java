package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.documentos.Localidad;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocalidadRepo extends MongoRepository<Localidad, String> {
}
