package co.edu.uniquindio.unieventos.servicios.impl;

import co.edu.uniquindio.unieventos.documentos.Cuenta;
import co.edu.uniquindio.unieventos.documentos.Evento;
import co.edu.uniquindio.unieventos.dto.evento.*;
import co.edu.uniquindio.unieventos.repositorios.CuentaRepo;
import co.edu.uniquindio.unieventos.repositorios.EventoRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.EventoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventoServicioImpl implements EventoServicio {
    private final EventoRepo eventoRepo;
    private final CuentaRepo cuentaRepo;

    public String crearEvento(CrearEventoDTO crearEventoDTO) throws Exception {
        if (existeNombre(crearEventoDTO.nombre())) {
            throw new Exception("El nombre ya existe, elija otro nombre");
        }
        Optional<Cuenta> optionalCuenta=cuentaRepo.findById(crearEventoDTO.idUsuario());

        if(optionalCuenta.isEmpty()){
            throw new Exception("La cuenta no existe");
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
        evento.setUbicacion(crearEventoDTO.ubicacion());


        eventoRepo.save(evento);

        return evento.getId();
    }

    @Override
    public String editarEvento(EditarEventoDTO editarEventoDTO) throws Exception {

        if (existeNombre(editarEventoDTO.nombre())) {
            throw new Exception("El nombre ya existe, elija otro nombre");
        }

        Evento evento = eventoRepo.findById(editarEventoDTO.id()).orElseThrow(() -> new Exception("No existe el evento"));

        evento.setNombre(editarEventoDTO.nombre());
        evento.setDescripcion(editarEventoDTO.descripcion());
        evento.setDireccion(editarEventoDTO.direccion());
        evento.setImagenPortada(editarEventoDTO.imagenPortada());
        evento.setImagenLocalidades(editarEventoDTO.imagenLocalidades());
        evento.setEstado(editarEventoDTO.estado());
        evento.setTipo(editarEventoDTO.tipo());
        evento.setFecha(editarEventoDTO.fecha());
        evento.setLocalidades(editarEventoDTO.localidades());

        eventoRepo.save(evento);

        return evento.getId();
    }

    @Override
    public String eliminarEvento(String id) throws Exception {
        if (!existeId(id)) {
            throw new Exception("No existe el evento");
        }

        eventoRepo.deleteById(id);

        return id;
    }

    @Override
    public InformacionEventoDTO obtenerInformacionEvento(String id) throws Exception {
        Evento evento = eventoRepo.findById(id).orElseThrow(() -> new Exception("No existe el evento"));

        return new InformacionEventoDTO(
                evento.getId(),
                evento.getNombre(),
                evento.getDescripcion(),
                evento.getDireccion(),
                evento.getCiudad(),
                evento.getImagenPortada(),
                evento.getImagenLocalidades(),
                evento.getTipo(),
                evento.getLocalidades(),
                evento.getEstado(),
                evento.getFecha(),
                evento.getUbicacion()
        );
    }

    @Override
    public List<ItemEventoDTO> listarEventos() {
        return eventoRepo.findAll().stream().map(evento -> new ItemEventoDTO(
                evento.getId(),
                evento.getNombre(),
                evento.getDescripcion(),
                evento.getImagenPortada(),
                evento.getFecha(),
                evento.getDireccion()
        )).toList();
    }

    @Override
    public boolean disponiblidad(DisponibilidadEventoDTO disponibilidadEventoDTO) throws Exception {
        Evento evento = eventoRepo.findById(disponibilidadEventoDTO.idEvento()).orElseThrow(() -> new Exception("No existe el evento"));

        return evento.
                getLocalidades().
                stream().
                anyMatch(localidad ->
                        localidad.getId().
                                equals(disponibilidadEventoDTO.idLocalidad()) && localidad.cantidadDisponible() >= disponibilidadEventoDTO.cantidad());
    }

    @Override
    public List<ItemEventoDTO> filtrarEventos(FiltroEventoDTO filtroEventoDTO) {
        return eventoRepo.findAll().stream().filter(evento ->
                evento.getTipo().equals(filtroEventoDTO.tipo()) &&
                evento.getCiudad().equals(filtroEventoDTO.ciudad()) &&
                evento.getNombre().equals(filtroEventoDTO.nombre())).
                map(evento -> new ItemEventoDTO(
                        evento.getId(),
                        evento.getNombre(),
                        evento.getDescripcion(),
                        evento.getImagenPortada(),
                        evento.getFecha(),
                        evento.getDireccion()
                )).toList();
    }

    @Override
    public String obtenerNombreArtista(String id) throws Exception {
        Evento evento = eventoRepo.findById(id).orElseThrow(() -> new Exception("No existe el evento"));


        return evento.getIdUsuario();
    }

    @Override
    public Evento obtenerEvento(String id) throws Exception {
        return eventoRepo.findById(id).orElseThrow(() -> new Exception("No existe el evento"));
    }

    public boolean existeId(String id){
        return eventoRepo.findById(id).isPresent();
    }
    public boolean existeNombre(String nombre){
        return eventoRepo.findByNombre(nombre).isPresent();
    }
}
