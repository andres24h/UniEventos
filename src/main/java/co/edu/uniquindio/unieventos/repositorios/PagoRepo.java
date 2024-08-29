package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.documentos.Pago;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PagoRepo extends MongoRepository<Pago, String> {
}
