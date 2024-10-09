package co.edu.uniquindio.unieventos.servicios.interfaces;

import co.edu.uniquindio.unieventos.documentos.Evento;
import co.edu.uniquindio.unieventos.dto.evento.*;

import java.util.List;

public interface EventoServicio {

    String crearEvento(CrearEventoDTO crearEventoDTO) throws Exception;

    String editarEvento(EditarEventoDTO editarEventoDTO) throws Exception;

    String eliminarEvento(String id) throws Exception;

    InformacionEventoDTO obtenerInformacionEvento(String id) throws Exception;

    List<ItemEventoDTO> listarEventos();

    boolean disponiblidad(DisponibilidadEventoDTO disponibilidadEventoDTO) throws Exception;

    List<ItemEventoDTO> filtrarEventos(FiltroEventoDTO filtroEventoDTO);

    String obtenerNombreArtista(String id) throws Exception;
    Evento obtenerEvento(String id) throws Exception;
}