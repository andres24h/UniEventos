package co.edu.uniquindio.unieventos.servicios.impl;

import co.edu.uniquindio.unieventos.documentos.Cupon;
import co.edu.uniquindio.unieventos.documentos.EstadoCupon;
import co.edu.uniquindio.unieventos.dto.cupon.ActualizarCuponDTO;
import co.edu.uniquindio.unieventos.dto.cupon.CrearCuponDTO;
import co.edu.uniquindio.unieventos.dto.cupon.ItemCuponDTO;
import co.edu.uniquindio.unieventos.repositorios.CuponRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuponServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CuponServicioImpl implements CuponServicio {

    private final CuponRepo cuponRepo;

    public CuponServicioImpl(CuponRepo cuponRepo) {
        this.cuponRepo = cuponRepo;
    }

    @Override
    public String crearCupones(CrearCuponDTO cuponDTO) {
        Cupon cupon = new Cupon(
                null, // ID será generado automáticamente
                cuponDTO.nombre(),
                cuponDTO.codigo(),
                cuponDTO.descuento(),
                cuponDTO.tipo(),
                cuponDTO.fechaVencimiento()
        );
        cuponRepo.save(cupon); // Guarda el cupon en la base de datos
        return cupon.getId(); // Retorna el ID del cupon creado
    }

    @Override
    public String actualizarCupon(ActualizarCuponDTO cuponDTO) {
        Cupon cupon = cuponRepo.findById(cuponDTO.id()).orElseThrow(() -> new RuntimeException("Cupón no encontrado"));
        cupon.setCodigo(cuponDTO.codigo());
        cupon.setNombre(cuponDTO.nombre());
        cupon.setDescuento(cuponDTO.descuento());
        cupon.setTipo(cuponDTO.tipoCupon());
        cupon.setFechaVencimiento(cuponDTO.fechaVencimiento().atStartOfDay());
        cupon.setEstado(cuponDTO.activo() ? EstadoCupon.DISPONIBLE : EstadoCupon.NO_DISPONIBLE);
        cuponRepo.save(cupon);
        return cupon.getId();
    }

    @Override
    public void borrarCupon(String idCupon) {
        cuponRepo.deleteById(idCupon);

    }

    @Override
    public boolean validarCupon(String idCupon, String userId) {
        // Implementación de validación del cupón
        return false;
    }

    @Override
    public String redimirCupon(String idCupon, String idCliente) {
        // Implementación de redención del cupón
        return "";

    }

    @Override
    public List<ItemCuponDTO> listarCupones(String idCupon, String idCliente) {
        return cuponRepo.findAll().stream().map(cupon -> new ItemCuponDTO(
                cupon.getId(),
                cupon.getCodigo(),
                cupon.getNombre(),
                cupon.getDescuento(),
                cupon.getFechaVencimiento(),
                cupon.getTipo()
        )).collect(Collectors.toList());
    }
}
