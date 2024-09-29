package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.documentos.Cupon;
import co.edu.uniquindio.unieventos.documentos.EstadoCupon;
import co.edu.uniquindio.unieventos.documentos.TipoCupon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CuponRepo extends MongoRepository<Cupon, String> {

    // Buscar cupones por su código
    Cupon findByCodigo(String codigo);

    // Buscar cupones por estado
    List<Cupon> findByEstado(EstadoCupon estado);

    // Ejemplo de búsqueda personalizada con @Query
    @Query("{ 'tipo' : ?0 }")
    List<Cupon> findByTipo(TipoCupon tipoCupon);
}
