package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.documentos.Cupon;
import co.edu.uniquindio.unieventos.documentos.EstadoCupon;
import co.edu.uniquindio.unieventos.documentos.TipoCupon;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CuponRepo extends MongoRepository<Cupon, String> {

    Optional<Cupon> findByCodigo(String codigo);
    Optional<Cupon> findByNombre(String nombre);
    Optional<Cupon> findById(ObjectId id);
    List<Cupon> findByBeneficiariosContains(String idCliente);

}
