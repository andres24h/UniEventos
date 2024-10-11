package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.documentos.Orden;
import co.edu.uniquindio.unieventos.dto.orden.ItemOrdenDTO;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface OrdenRepo extends MongoRepository<Orden, String> {
    @Aggregation({
            "{ $match: { idCliente: ?0 } }", // Coincidir por idCliente
            "{ $lookup: { from: 'cuentas', localField: 'idCliente', foreignField: '_id', as: 'cuenta' } }", // Unir con la colección 'cuentas'
            "{ $unwind: '$cuenta' }", // Descomponer el array de cuentas
            "{ $project: { fecha: 1, estado: 1, 'pago.estado': 1, nombreUsuario: '$cuenta.usuario.nombre', correoUsuario: '$cuenta.email' } }" // Proyectar los datos de la cuenta
    })
    List<ItemOrdenDTO> listarOrdenes(String idCliente);
    List <Orden> findByIdCliente(String idCliente);
    List<Orden> findByItemsIdEvento(String items_idEvento);
}
