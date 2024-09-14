package co.edu.uniquindio.unieventos.servicios.impl;

import co.edu.uniquindio.unieventos.dto.evento.CrearEventoDTO;
import co.edu.uniquindio.unieventos.repositorios.EventoRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventoServicioImpl {
    EventoRepo eventoRepo;


    public String crearEvento(CrearEventoDTO crearEventoDTO)throws Exception{
        if(existeNombre(crearEventoDTO.nombre())){
            throw new Exception("El nombre ya existe elija otro nombre");
        }


    }


    public boolean existeId(String id){
        return eventoRepo.findById(id).isPresent();
    }
    public boolean existeNombre(String nombre){
        return eventoRepo.findByNombre(nombre).isPresent();
    }
}
