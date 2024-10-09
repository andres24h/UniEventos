package co.edu.uniquindio.unieventos.controladores;

import co.edu.uniquindio.unieventos.documentos.Orden;
import co.edu.uniquindio.unieventos.dto.global.MensajeDTO;
import co.edu.uniquindio.unieventos.dto.orden.CrearOrdenDTO;
import co.edu.uniquindio.unieventos.dto.orden.ItemOrdenDTO;
import co.edu.uniquindio.unieventos.servicios.interfaces.OrdenServicio;
import com.mercadopago.resources.preference.Preference;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orden")
public class OrdenControlador {
    private final OrdenServicio ordenServicio;


    @PostMapping("/realizar-pago/{idOrden}")
    public ResponseEntity<MensajeDTO<Preference>> realizarPago(@PathVariable String idOrden) throws Exception {
        Preference preference = ordenServicio.realizarPago(idOrden);
        return ResponseEntity.ok(new MensajeDTO<>(false, preference));
    }


    @PostMapping("/notificacion")
    public ResponseEntity<Void> recibirNotificacionMercadoPago(@RequestBody Map<String, Object> request) {
        ordenServicio.recibirNotificacionMercadoPago(request);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/crear")
    public ResponseEntity<MensajeDTO<String>> crearOrden(@Valid @RequestBody CrearOrdenDTO crearOrdenDTO) throws Exception {
        String resultado = ordenServicio.crearOrden(crearOrdenDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, resultado));
    }


    @GetMapping("/obtener/{idOrden}")
    public ResponseEntity<MensajeDTO<Orden>> obtenerOrden(@PathVariable String idOrden) throws Exception {
        Orden orden = ordenServicio.obtenerOrden(idOrden);
        return ResponseEntity.ok(new MensajeDTO<>(false, orden));
    }


    @GetMapping("/listar-usuario/{idUsuario}")
    public ResponseEntity<MensajeDTO<List<ItemOrdenDTO>>> listarOrdenByUsuario(@PathVariable String idUsuario) {
        List<ItemOrdenDTO> lista = ordenServicio.listOrdenByUsuario(idUsuario);
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }


    @DeleteMapping("/cancelar/{idOrden}")
    public ResponseEntity<MensajeDTO<String>> cancelarOrden(@PathVariable String idOrden) throws Exception {
        ordenServicio.cancelarOrden(idOrden);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Orden cancelada exitosamente"));
    }


    @PostMapping("/procesar-pago")
    public ResponseEntity<Void> procesarPago(@RequestParam String noclaro) {
        ordenServicio.procesarPago(noclaro);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/generar-qr/{idOrden}")
    public ResponseEntity<MensajeDTO<String>> generarQRC(@PathVariable String idOrden) {
        String qrCode = ordenServicio.generarQRC(idOrden);
        return ResponseEntity.ok(new MensajeDTO<>(false, qrCode));
    }

    @GetMapping("/ordenes-evento/{idEvento}")
    public ResponseEntity<List<Orden>> obtenerOrdenesPorEvento(@PathVariable String idEvento) {
        List<ItemOrdenDTO> ordenes = ordenServicio.listOrdenByEvento(idEvento);
        return ResponseEntity.ok().build();
    }

}
