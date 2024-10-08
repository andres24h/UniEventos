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

    // Método para realizar un pago a través de MercadoPago
    @PostMapping("/realizar-pago/{idOrden}")
    public ResponseEntity<MensajeDTO<Preference>> realizarPago(@PathVariable String idOrden) throws Exception {
        Preference preference = ordenServicio.realizarPago(idOrden);
        return ResponseEntity.ok(new MensajeDTO<>(false, preference));
    }

    // Método para recibir notificación de MercadoPago
    @PostMapping("/notificacion")
    public ResponseEntity<Void> recibirNotificacionMercadoPago(@RequestBody Map<String, Object> request) {
        ordenServicio.recibirNotificacionMercadoPago(request);
        return ResponseEntity.ok().build();
    }

    // Método para crear una orden
    @PostMapping("/crear")
    public ResponseEntity<MensajeDTO<String>> crearOrden(@Valid @RequestBody CrearOrdenDTO crearOrdenDTO) throws Exception {
        String resultado = ordenServicio.crearOrden(crearOrdenDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, resultado));
    }

    // Método para obtener una orden por su ID
    @GetMapping("/obtener/{idOrden}")
    public ResponseEntity<MensajeDTO<Orden>> obtenerOrden(@PathVariable String idOrden) throws Exception {
        Orden orden = ordenServicio.obtenerOrden(idOrden);
        return ResponseEntity.ok(new MensajeDTO<>(false, orden));
    }

    // Método para listar órdenes de un usuario
    @GetMapping("/listar-usuario/{idUsuario}")
    public ResponseEntity<MensajeDTO<List<ItemOrdenDTO>>> listarOrdenByUsuario(@PathVariable String idUsuario) {
        List<ItemOrdenDTO> lista = ordenServicio.listOrdenByUsuario(idUsuario);
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }

    // Método para cancelar una orden
    @DeleteMapping("/cancelar/{idOrden}")
    public ResponseEntity<MensajeDTO<String>> cancelarOrden(@PathVariable String idOrden) throws Exception {
        ordenServicio.cancelarOrden(idOrden);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Orden cancelada exitosamente"));
    }

    // Método para procesar un pago (simulación de procesamiento)
    @PostMapping("/procesar-pago")
    public ResponseEntity<Void> procesarPago(@RequestParam String noclaro) {
        ordenServicio.procesarPago(noclaro);
        return ResponseEntity.ok().build();
    }

    // Método para generar un código QR para una orden
    @GetMapping("/generar-qr/{idOrden}")
    public ResponseEntity<MensajeDTO<String>> generarQRC(@PathVariable String idOrden) {
        String qrCode = ordenServicio.generarQRC(idOrden);
        return ResponseEntity.ok(new MensajeDTO<>(false, qrCode));
    }
}
