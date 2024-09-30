package co.edu.uniquindio.unieventos.servicios.impl;

import co.edu.uniquindio.unieventos.documentos.Cupon;
import co.edu.uniquindio.unieventos.documentos.EstadoCupon;
import co.edu.uniquindio.unieventos.documentos.TipoCupon;
import co.edu.uniquindio.unieventos.dto.cupon.ActualizarCuponDTO;
import co.edu.uniquindio.unieventos.dto.cupon.CrearCuponDTO;
import co.edu.uniquindio.unieventos.dto.cupon.ItemCuponDTO;
import co.edu.uniquindio.unieventos.repositorios.CuponRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuponServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

        if (cuponDTO.fechaVencimiento().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("La fecha de vencimiento no puede ser en el pasado");
        }
        Cupon cupon;
        cupon = new Cupon();

        if (cuponDTO.tipo() == TipoCupon.UNICO) {
            cupon.setCodigo(cuponDTO.codigo());
            cupon.setNombre(cuponDTO.nombre());
            cupon.setDescuento(cuponDTO.descuento());
            cupon.setEstado(cuponDTO.estado());
            cupon.setTipo(cuponDTO.tipo());
            cupon.setFechaVencimiento(cuponDTO.fechaVencimiento());
            cupon.setBeneficiarios(cuponDTO.beneficiarios());
        } else {
            String codigoIndividual = generarCodigoIndividual();
            cupon.setCodigo(codigoIndividual);
            cupon.setNombre(cuponDTO.nombre());
            cupon.setDescuento(cuponDTO.descuento());
            cupon.setEstado(cuponDTO.estado());
            cupon.setTipo(cuponDTO.tipo());
            cupon.setFechaVencimiento(cuponDTO.fechaVencimiento());
            cupon.setBeneficiarios(cuponDTO.beneficiarios());
        }

        cuponRepo.save(cupon);
        return cupon.getId().toString();
    }
    @Override
    public String actualizarCupon(ActualizarCuponDTO actualizarCuponDTO) throws Exception {
        Optional<Cupon> Optionalcupon = cuponRepo.findById(actualizarCuponDTO.id());
        if(Optionalcupon.isEmpty()){
            throw new Exception("El cupon no existe");
        }
        Cupon cupon= Optionalcupon.get();
        cupon.setNombre(actualizarCuponDTO.nombre());
        cupon.setDescuento(actualizarCuponDTO.descuento());
        cupon.setEstado(actualizarCuponDTO.estadoCupon());
        cupon.setTipo(actualizarCuponDTO.tipoCupon());
        cupon.setFechaVencimiento(actualizarCuponDTO.fechaVencimiento());


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
    private String generarCodigoIndividual() {
        return "CUPON-" + UUID.randomUUID().toString();
    }


    public boolean verificarDisponibilidadCupon(String idCupon) {
        Optional<Cupon> cuponOptional = cuponRepo.findById(idCupon);
        if (cuponOptional.isEmpty()) {
            return false;
        }
        Cupon cupon = cuponOptional.get();

        return cupon.getEstado() == EstadoCupon.DISPONIBLE &&
                cupon.getFechaVencimiento().isAfter(LocalDateTime.now());
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
