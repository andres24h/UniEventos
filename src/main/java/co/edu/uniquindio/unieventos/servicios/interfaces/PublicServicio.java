package co.edu.uniquindio.unieventos.servicios.interfaces;



import co.edu.uniquindio.unieventos.documentos.Departamentos;
import co.edu.uniquindio.unieventos.documentos.EstadoEvento;
import co.edu.uniquindio.unieventos.documentos.TipoEvento;

import java.util.List;

public interface PublicServicio {

    List<Departamentos> listarCiudades();

    List<TipoEvento> listarTipo();

    List<EstadoEvento> listarEstado();

}