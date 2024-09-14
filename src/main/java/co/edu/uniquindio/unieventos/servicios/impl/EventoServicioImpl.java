package co.edu.uniquindio.unieventos.servicios.impl;

import co.edu.uniquindio.unieventos.documentos.Evento;
import co.edu.uniquindio.unieventos.dto.evento.CrearEventoDTO;
import co.edu.uniquindio.unieventos.repositorios.EventoRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventoServicioImpl {
    EventoRepo eventoRepo;


    public String crearEvento(CrearEventoDTO crearEventoDTO) throws Exception {
        // Verifica si ya existe un evento con el mismo nombre
        if (existeNombre(crearEventoDTO.nombre())) {
            throw new Exception("El nombre ya existe, elija otro nombre");
        }

        Evento evento = new Evento();
        evento.setIdUsuario(crearEventoDTO.idUsuario());
        evento.setNombre(crearEventoDTO.nombre());
        evento.setDescripcion(crearEventoDTO.descripcion());
        evento.setDireccion(crearEventoDTO.direccion());
        evento.setCiudad(crearEventoDTO.ciudad());
        evento.setImagenPortada(crearEventoDTO.imagenPortada());
        evento.setImagenLocalidades(crearEventoDTO.imagenLocalidades());
        evento.setEstado(crearEventoDTO.estado());
        evento.setTipo(crearEventoDTO.tipo());
        evento.setFecha(crearEventoDTO.fecha());
        evento.setLocalidades(crearEventoDTO.localidades());


        eventoRepo.save(evento);

        return evento.getId();
    }

    public boolean existeId(String id){
        return eventoRepo.findById(id).isPresent();
    }
    public boolean existeNombre(String nombre){
        return eventoRepo.findByNombre(nombre).isPresent();
    }
}
