package co.edu.uniquindio.unieventos.controladores;

import co.edu.uniquindio.unieventos.dto.cuenta.*;
import co.edu.uniquindio.unieventos.dto.global.MensajeDTO;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuentaServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cuenta")
public class CuentaControlador {
    private final CuentaServicio cuentaServicio;

    // Método para crear una cuenta
    @PostMapping("/crear")
    public ResponseEntity<MensajeDTO<String>> crearCuenta(@Valid @RequestBody CrearCuentaDTO cuenta) throws Exception {
        String resultado = cuentaServicio.crearCuenta(cuenta);
        return ResponseEntity.ok(new MensajeDTO<>(false, resultado));
    }

    // Método para activar la cuenta
    @PutMapping("/activar")
    public ResponseEntity<MensajeDTO<String>> activarCuenta(@Valid @RequestBody ActivarCuentaDTO activarCuentaDTO) throws Exception {
        boolean activada = cuentaServicio.activarCuenta(activarCuentaDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, activada ? "Cuenta activada exitosamente" : "Error activando la cuenta"));
    }

    // Método para enviar código de recuperación de contraseña
    @PostMapping("/recuperar-password")
    public ResponseEntity<MensajeDTO<String>> enviarCodigoRecuperacionPassword(@RequestParam String correo) throws Exception {
        String resultado = cuentaServicio.enviarCodigoRecuperacionPassword(correo);
        return ResponseEntity.ok(new MensajeDTO<>(false, resultado));
    }

    // Método para cambiar la contraseña
    @PutMapping("/cambiar-password")
    public ResponseEntity<MensajeDTO<String>> cambiarPassword(@Valid @RequestBody CambiarPasswordDTO cambiarPasswordDTO) throws Exception {
        String resultado = cuentaServicio.cambiarPassword(cambiarPasswordDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, resultado));
    }

    // Método para iniciar sesión
    @PostMapping("/iniciar-sesion")
    public ResponseEntity<MensajeDTO<TokenDTO>> iniciarSesion(@Valid @RequestBody LoginDTO loginDTO) throws Exception {
        TokenDTO token = cuentaServicio.iniciarSesion(loginDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, token));
    }

    // Método para agregar evento al carrito
    @PostMapping("/carrito/agregar")
    public ResponseEntity<MensajeDTO<String>> agregarEventoCarrito(@Valid @RequestBody AgregarEventoDTO agregarEventoDTO) throws Exception {
        String resultado = cuentaServicio.agregarEventoCarrito(agregarEventoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, resultado));
    }

    // Método para eliminar evento del carrito
    @DeleteMapping("/carrito/eliminar")
    public ResponseEntity<MensajeDTO<String>> eliminarEventoCarrito(@Valid @RequestBody EliminarEventoDTO eliminarEventoDTO) throws Exception {
        String resultado = cuentaServicio.eliminarEventoCarrito(eliminarEventoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, resultado));
    }

    // Métodos ya existentes
    @PutMapping("/editar-perfil")
    public ResponseEntity<MensajeDTO<String>> editarCuenta(@Valid @RequestBody EditarCuentaDTO cuenta) throws Exception{
        cuentaServicio.editarCuenta(cuenta);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta editada exitosamente"));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarCuenta(@PathVariable String id) throws Exception{
        cuentaServicio.eliminarCuenta(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta eliminada exitosamente"));
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<MensajeDTO<InformacionCuentaDTO>> obtenerInformacionCuenta(@PathVariable String id) throws Exception {
        InformacionCuentaDTO info = cuentaServicio.obtenerInformacionCuenta(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, info));
    }

    @GetMapping("/listar-todo")
    public ResponseEntity<MensajeDTO<List<ItemCuentaDTO>>> listarCuentas(){
        List<ItemCuentaDTO> lista = cuentaServicio.listarCuentas();
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }
}

