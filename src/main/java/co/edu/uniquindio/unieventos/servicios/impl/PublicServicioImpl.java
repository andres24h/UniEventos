package co.edu.uniquindio.unieventos.servicios.impl;

import co.edu.uniquindio.unieventos.documentos.Departamentos;
import co.edu.uniquindio.unieventos.documentos.TipoEvento;
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
}