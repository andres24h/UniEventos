package co.edu.uniquindio.unieventos.controladores;

import co.edu.uniquindio.unieventos.dto.cuenta.CrearCuentaDTO;
import co.edu.uniquindio.unieventos.dto.cuenta.EditarCuentaDTO;
import co.edu.uniquindio.unieventos.dto.cuenta.InformacionCuentaDTO;
import co.edu.uniquindio.unieventos.dto.cuenta.ItemCuentaDTO;
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
