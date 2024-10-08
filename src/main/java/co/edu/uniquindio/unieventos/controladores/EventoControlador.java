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

    // Método para crear un evento
    @PostMapping("/crear")
    public ResponseEntity<MensajeDTO<String>> crearEvento(@Valid @RequestBody CrearEventoDTO crearEventoDTO) throws Exception {
        String resultado = eventoServicio.crearEvento(crearEventoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, resultado));
    }

    // Método para editar un evento
    @PutMapping("/editar")
    public ResponseEntity<MensajeDTO<String>> editarEvento(@Valid @RequestBody EditarEventoDTO editarEventoDTO) throws Exception {
        String resultado = eventoServicio.editarEvento(editarEventoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, resultado));
    }

    // Método para eliminar un evento
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarEvento(@PathVariable String id) throws Exception {
        String resultado = eventoServicio.eliminarEvento(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, resultado));
    }

    // Método para obtener información de un evento específico
    @GetMapping("/obtener/{id}")
    public ResponseEntity<MensajeDTO<InformacionEventoDTO>> obtenerInformacionEvento(@PathVariable String id) throws Exception {
        InformacionEventoDTO info = eventoServicio.obtenerInformacionEvento(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, info));
    }

    // Método para listar todos los eventos
    @GetMapping("/listar-todo")
    public ResponseEntity<MensajeDTO<List<ItemEventoDTO>>> listarEventos() {
        List<ItemEventoDTO> lista = eventoServicio.listarEventos();
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }

    // Método para verificar la disponibilidad de un evento
    @PostMapping("/disponibilidad")
    public ResponseEntity<MensajeDTO<Boolean>> disponibilidad(@Valid @RequestBody DisponibilidadEventoDTO disponibilidadEventoDTO) throws Exception {
        boolean disponible = eventoServicio.disponiblidad(disponibilidadEventoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, disponible));
    }

    // Método para filtrar eventos según ciertos criterios
    @PostMapping("/filtrar")
    public ResponseEntity<MensajeDTO<List<ItemEventoDTO>>> filtrarEventos(@Valid @RequestBody FiltroEventoDTO filtroEventoDTO) {
        List<ItemEventoDTO> lista = eventoServicio.filtrarEventos(filtroEventoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }

    // Método para obtener el nombre del artista de un evento específico
    @GetMapping("/obtener-artista/{id}")
    public ResponseEntity<MensajeDTO<String>> obtenerNombreArtista(@PathVariable String id) throws Exception {
        String nombreArtista = eventoServicio.obtenerNombreArtista(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, nombreArtista));
    }
}
