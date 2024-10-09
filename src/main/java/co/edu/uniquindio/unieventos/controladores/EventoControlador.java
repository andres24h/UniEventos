package co.edu.uniquindio.unieventos.controladores;

import co.edu.uniquindio.unieventos.dto.evento.*;
import co.edu.uniquindio.unieventos.dto.global.MensajeDTO;
import co.edu.uniquindio.unieventos.servicios.interfaces.EventoServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/evento")
public class EventoControlador {
    private final EventoServicio eventoServicio;

/**
 *
 * @param
 * @return
 * @throws Exception


    @PostMapping("/crear-evento")
    public ResponseEntity<MensajeDTO<String>> crearEvento(@Valid @RequestBody CrearEventoDTO crearEventoDTO) throws Exception {
        String resultado = eventoServicio.crearEvento(crearEventoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, resultado));
    }

    @PutMapping("/editar-evento")
    public ResponseEntity<MensajeDTO<String>> editarEvento(@Valid @RequestBody EditarEventoDTO editarEventoDTO) throws Exception {
        String resultado = eventoServicio.editarEvento(editarEventoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, resultado));
    }


    @DeleteMapping("/eliminar-evento/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarEvento(@PathVariable String id) throws Exception {
        String resultado = eventoServicio.eliminarEvento(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, resultado));
    }

 */
    @GetMapping("/obtener-evento/{id}")
    public ResponseEntity<MensajeDTO<InformacionEventoDTO>> obtenerInformacionEvento(@PathVariable String id) throws Exception {
        InformacionEventoDTO info = eventoServicio.obtenerInformacionEvento(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, info));
    }


    @GetMapping("/listar-todo-evento")
    public ResponseEntity<MensajeDTO<List<ItemEventoDTO>>> listarEventos() {
        List<ItemEventoDTO> lista = eventoServicio.listarEventos();
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }


    @PostMapping("/disponibilidad-evento")
    public ResponseEntity<MensajeDTO<Boolean>> disponibilidad(@Valid @RequestBody DisponibilidadEventoDTO disponibilidadEventoDTO) throws Exception {
        boolean disponible = eventoServicio.disponiblidad(disponibilidadEventoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, disponible));
    }


    @PostMapping("/filtrar-evento")
    public ResponseEntity<MensajeDTO<List<ItemEventoDTO>>> filtrarEventos(@Valid @RequestBody FiltroEventoDTO filtroEventoDTO) {
        List<ItemEventoDTO> lista = eventoServicio.filtrarEventos(filtroEventoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }


    @GetMapping("/obtener-artista-evento/{id}")
    public ResponseEntity<MensajeDTO<String>> obtenerNombreArtista(@PathVariable String id) throws Exception {
        String nombreArtista = eventoServicio.obtenerNombreArtista(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, nombreArtista));
    }
}
