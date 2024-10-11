package co.edu.uniquindio.unieventos.controladores;

import co.edu.uniquindio.unieventos.documentos.TipoEvento;
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


    @GetMapping("/filtrar-evento")
    public ResponseEntity<MensajeDTO<List<ItemEventoDTO>>> filtrarEventos(@Valid @RequestBody FiltroEventoDTO filtroEventoDTO) {
        List<ItemEventoDTO> lista = eventoServicio.filtrarEventos(filtroEventoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }
    @GetMapping("/filtrar-por-ciudad/{ciudad}")
    public ResponseEntity<MensajeDTO<List<ItemEventoDTO>>> filtrarEventosPorCiudad(@PathVariable String ciudad) {
        List<ItemEventoDTO> lista = eventoServicio.filtrarEventosPorCiudad(ciudad);
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }

    @GetMapping("/filtrar-por-tipo/{tipo}")
    public ResponseEntity<MensajeDTO<List<ItemEventoDTO>>> filtrarEventosPorTipo(@PathVariable TipoEvento tipo) {
        List<ItemEventoDTO> lista = eventoServicio.filtrarEventosPorTipo(tipo);
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }

    @GetMapping("/filtrar-por-nombre/{nombre}")
    public ResponseEntity<MensajeDTO<List<ItemEventoDTO>>> filtrarEventosPorNombre(@PathVariable String nombre) {
        List<ItemEventoDTO> lista = eventoServicio.filtrarEventosPorNombre(nombre);
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }

    @PostMapping("/filtrar-por-nombre-y-ciudad")
    public ResponseEntity<MensajeDTO<List<ItemEventoDTO>>> filtrarEventosPorNombreYCiudad(@Valid @RequestBody FiltrarPorNombreYCiudadDTO filtro) {
        List<ItemEventoDTO> lista = eventoServicio.filtrarEventosPorNombreYCiudad(filtro);
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }

    @PostMapping("/filtrar-por-fecha")
    public ResponseEntity<MensajeDTO<List<ItemEventoDTO>>> filtrarEventosPorFecha(@Valid @RequestBody FiltrarPorFechaDTO filtro) {
        List<ItemEventoDTO> lista = eventoServicio.filtrarEventosPorFecha(filtro);
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }

    @PostMapping("/filtrar-por-fecha-y-ciudad")
    public ResponseEntity<MensajeDTO<List<ItemEventoDTO>>> filtrarEventosPorFechaYCiudad(@Valid @RequestBody FiltrarPorFechaYCiudadDTO filtro) {
        List<ItemEventoDTO> lista = eventoServicio.filtrarEventosPorFechaYCiudad(filtro);
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }

    @PostMapping("/filtrar-por-fecha-y-tipo")
    public ResponseEntity<MensajeDTO<List<ItemEventoDTO>>> filtrarEventosPorFechaYTipo(@Valid @RequestBody FiltrarPorFechaYTipoDTO filtro) {
        List<ItemEventoDTO> lista = eventoServicio.filtrarEventosPorFechaYTipo(filtro);
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }

    @PostMapping("/filtrar-por-fecha-tipo-y-ciudad")
    public ResponseEntity<MensajeDTO<List<ItemEventoDTO>>> filtrarEventosPorFechaYTipoYCiudad(@Valid @RequestBody FiltrarPorFechaYTipoYCiudadDTO filtro) {
        List<ItemEventoDTO> lista = eventoServicio.filtrarEventosPorFechaYTipoYCiudad(filtro);
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }

    @PostMapping("/filtrar-por-tipo-y-ciudad")
    public ResponseEntity<MensajeDTO<List<ItemEventoDTO>>> filtrarEventosPorTipoYCiudad(@Valid @RequestBody FiltrarPorTipoYCiudadDTO filtro) {
        List<ItemEventoDTO> lista = eventoServicio.filtrarEventosPorTipoYCiudad(filtro);
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }
}


