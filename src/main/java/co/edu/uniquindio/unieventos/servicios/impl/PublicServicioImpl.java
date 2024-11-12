package co.edu.uniquindio.unieventos.servicios.impl;

import co.edu.uniquindio.unieventos.documentos.*;
import co.edu.uniquindio.unieventos.servicios.interfaces.PublicServicio;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PublicServicioImpl implements PublicServicio {

    @Override
    public List<Departamentos> listarCiudades() {
        return Arrays.asList(Departamentos.values());
    }

    @Override
    public List<TipoEvento> listarTipo() {
        return Arrays.asList(TipoEvento.values());
    }

    @Override
    public List<EstadoEvento> listarEstado() {
        return Arrays.asList(EstadoEvento.values());
    }

    @Override
    public List<EstadoCupon> listarEstadoCupon() {
        return Arrays.asList(EstadoCupon.values());
    }
    @Override
    public List<TipoCupon> listarTipoCupon() {
        return Arrays.asList(TipoCupon.values());
    }
}