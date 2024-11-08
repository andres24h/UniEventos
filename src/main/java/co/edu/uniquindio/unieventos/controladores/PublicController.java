package co.edu.uniquindio.unieventos.controladores;


import co.edu.uniquindio.unieventos.documentos.Departamentos;
import co.edu.uniquindio.unieventos.documentos.EstadoEvento;
import co.edu.uniquindio.unieventos.documentos.TipoEvento;
import co.edu.uniquindio.unieventos.dto.global.MensajeDTO;
import co.edu.uniquindio.unieventos.servicios.interfaces.PublicServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/publico")
@RequiredArgsConstructor
public class PublicController {

    private final PublicServicio publicServicio;

    @GetMapping("/listar-ciudades")
    public ResponseEntity<MensajeDTO<List<Departamentos>>> listarCiudades(){
        return ResponseEntity.ok().body( new MensajeDTO<>(false, publicServicio.listarCiudades()));
    }

    @GetMapping("/listar-tipo")
    public ResponseEntity<MensajeDTO<List<TipoEvento>>> listarTipoEvento(){
        return ResponseEntity.ok().body( new MensajeDTO<>(false, publicServicio.listarTipo()));
    }

    @GetMapping("/listar-estado")
    public ResponseEntity<MensajeDTO<List<EstadoEvento>>> listarEstado(){
        return ResponseEntity.ok().body( new MensajeDTO<>(false, publicServicio.listarEstado()));
    }
}