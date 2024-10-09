package co.edu.uniquindio.unieventos.controladores;

import co.edu.uniquindio.unieventos.dto.cuenta.*;
import co.edu.uniquindio.unieventos.dto.cupon.RedimirCuponDTO;
import co.edu.uniquindio.unieventos.dto.global.MensajeDTO;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuentaServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuponServicio;
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
    private final CuponServicio cuponServicio;


    @PostMapping("/carrito-agregar")
    public ResponseEntity<MensajeDTO<String>> agregarEventoCarrito(@Valid @RequestBody AgregarEventoDTO agregarEventoDTO) throws Exception {
        String resultado = cuentaServicio.agregarEventoCarrito(agregarEventoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, resultado));
    }


    @DeleteMapping("/carrito-eliminar")
    public ResponseEntity<MensajeDTO<String>> eliminarEventoCarrito(@Valid @RequestBody EliminarEventoDTO eliminarEventoDTO) throws Exception {
        String resultado = cuentaServicio.eliminarEventoCarrito(eliminarEventoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, resultado));
    }


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

    @PostMapping("/redimir-cupon")
    public ResponseEntity<MensajeDTO<String>> redimirCupon(@Valid @RequestBody RedimirCuponDTO redimirCuponDTO) throws Exception {
        boolean redimido = cuponServicio.redimirCupon(redimirCuponDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, redimido ? "Cupón redimido exitosamente" : "No se pudo redimir el cupón"));
    }

}

