package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.documentos.Orden;
import co.edu.uniquindio.unieventos.dto.orden.ItemOrdenDTO;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface OrdenRepo extends MongoRepository<Orden, String> {
    @Aggregation({
            "{ $match: { idUsuario: ?0 } }",
            "{ $lookup: { from: 'usuarios', localField: 'idUsuario', foreignField: '_id', as: 'usuario' } }",
            "{ $unwind: '$usuario' }",
            "{ $project: { fecha: '$fecha', estado: '$estado', pago: '$pago', nombreUsuario: '$usuario.nombre', correoUsuario: '$usuario.email' } }"
    })
    List<ItemOrdenDTO> listarOrdenes(String codigoCliente);
    Optional<Orden> findByIdCliente(String idCliente);
    List<Orden> findByItemsIdEvento(String items_idEvento);
}
