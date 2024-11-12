package co.edu.uniquindio.unieventos.servicios.interfaces;



import co.edu.uniquindio.unieventos.documentos.*;

import java.util.List;

public interface PublicServicio {

    List<Departamentos> listarCiudades();

    List<TipoEvento> listarTipo();

    List<EstadoEvento> listarEstado();

    List<EstadoCupon> listarEstadoCupon();

    List<TipoCupon> listarTipoCupon();

}