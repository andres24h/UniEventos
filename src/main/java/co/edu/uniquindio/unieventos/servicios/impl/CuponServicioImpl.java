package co.edu.uniquindio.unieventos.servicios.impl;

import co.edu.uniquindio.unieventos.documentos.*;
import co.edu.uniquindio.unieventos.dto.cupon.*;
import co.edu.uniquindio.unieventos.repositorios.CuentaRepo;
import co.edu.uniquindio.unieventos.repositorios.CuponRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuponServicio;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class CuponServicioImpl implements CuponServicio {
    private final CuponRepo cuponRepo;
    private final CuentaRepo cuentaRepo;
    public CuponServicioImpl(CuponRepo cuponRepo, CuentaRepo cuentaRepo) {
        this.cuponRepo = cuponRepo;
        this.cuentaRepo = cuentaRepo;
    }


    @Override
    public String crearCupones(CrearCuponDTO cuponDTO)throws Exception {

        if (cuponDTO.fechaVencimiento().isBefore(LocalDateTime.now())) {
            throw new Exception("La fecha de vencimiento no puede ser en el pasado");
        }

        if(existeCodigo(cuponDTO.codigo())){
            throw new Exception("el codigo ya existe:"+cuponDTO.codigo());
        }
        if(existeNombre(cuponDTO.nombre())){
            throw new Exception("el nombre ya existe:"+cuponDTO.nombre());
        }

        Cupon cupon;
        cupon = new Cupon();

        if (cuponDTO.tipo() == TipoCupon.UNICO) {
            cupon.setCodigo(cuponDTO.codigo());
            cupon.setNombre(cuponDTO.nombre());
            cupon.setDescuento(cuponDTO.descuento());
            cupon.setEstado(EstadoCupon.DISPONIBLE);
            cupon.setTipo(cuponDTO.tipo());
            cupon.setFechaVencimiento(cuponDTO.fechaVencimiento());

        } else {
            String codigoIndividual = generarCodigoIndividual();
            cupon.setCodigo(codigoIndividual);
            cupon.setNombre(cuponDTO.nombre());
            cupon.setDescuento(cuponDTO.descuento());
            cupon.setEstado(EstadoCupon.DISPONIBLE);
            cupon.setTipo(cuponDTO.tipo());
            cupon.setFechaVencimiento(cuponDTO.fechaVencimiento());
            cupon.setBeneficiarios(cuponDTO.beneficiarios());
        }

        cuponRepo.save(cupon);
        return cupon.getCodigo();
    }

    @Override
    public String actualizarCupon(ActualizarCuponDTO actualizarCuponDTO) throws Exception {
        Optional<Cupon> Optionalcupon = cuponRepo.findById(actualizarCuponDTO.id());

        if (Optionalcupon.isEmpty()) {
            throw new Exception("El cupon no existe");
        }
        if(existeNombre(actualizarCuponDTO.nombre())){
            throw new Exception("El nombre ya existe pruebe otro");
        }
        Cupon cupon = Optionalcupon.get();
        cupon.setNombre(actualizarCuponDTO.nombre());
        cupon.setDescuento(actualizarCuponDTO.descuento());
        cupon.setEstado(actualizarCuponDTO.estadoCupon());
        cupon.setBeneficiarios(actualizarCuponDTO.beneficiarios());

        if (actualizarCuponDTO.fechaVencimiento().isBefore(LocalDateTime.now())) {
            throw new Exception("La fecha de vencimiento no puede ser en el pasado");
        }

        cupon.setFechaVencimiento(actualizarCuponDTO.fechaVencimiento());

        cuponRepo.save(cupon);

        return cupon.getId();
    }

    @Override
    public void borrarCupon(String idCupon)throws Exception {
        Optional<Cupon> cuponOptional=cuponRepo.findById(idCupon);
        if(cuponOptional.isEmpty()){
            throw new Exception("El cupon no existe :) ");
        }
        Cupon cupon=cuponOptional.get();
        cupon.setEstado(EstadoCupon.NO_DISPONIBLE);
        cuponRepo.save(cupon);

    }

    @Override
    public boolean redimirCupon(RedimirCuponDTO redimirCuponDTO)throws Exception {
        Optional<Cupon> cuponOptional = cuponRepo.findByCodigo(redimirCuponDTO.codigoCupon());
        if (cuponOptional.isEmpty()) {
            throw new Exception("El cupón no existe");
        }

        if (!verificarDisponibilidadCupon(redimirCuponDTO.codigoCupon())) {
            throw new Exception("El cupón ya no se encuentra disponible");
        }

        Cupon cupon = cuponOptional.get();
        if(cupon.getEstado().equals(EstadoCupon.NO_DISPONIBLE)){
            throw new Exception("El cupon no se encuentra disponible");
        }
        if(cupon.getBeneficiarios()==null){
            cupon.setBeneficiarios(new ArrayList<>());
        }

        if(cupon.getTipo().equals(TipoCupon.UNICO)){
            List<String> beneficiarios = cupon.getBeneficiarios();
            beneficiarios.add(redimirCuponDTO.idCliente());
            cupon.setEstado(EstadoCupon.NO_DISPONIBLE);
            cuponRepo.save(cupon);
        }
        else{
            if (!cupon.getBeneficiarios().contains(redimirCuponDTO.idCliente())) {
                throw new IllegalArgumentException("El cliente no cuenta con este cupon");
            }
            List<String> beneficiarios = cupon.getBeneficiarios();
            beneficiarios.remove(redimirCuponDTO.idCliente());
            cupon.setBeneficiarios(beneficiarios);
            cuponRepo.save(cupon);
        }
        return true;
    }




    public boolean verificarDisponibilidadCupon(String codigoCupon) {
        Optional<Cupon> cuponOptional = cuponRepo.findByCodigo(codigoCupon);
        if (cuponOptional.isEmpty()) {
            return false;
        }
        Cupon cupon = cuponOptional.get();

        if(cupon.getEstado() == EstadoCupon.DISPONIBLE &&
                cupon.getFechaVencimiento().isAfter(LocalDateTime.now())){
            return true;
        }
        return false;
    }

    @Override
    public List<ItemCuponDTO> listarCupones() {
        return cuponRepo.findAll().stream()
                .filter(cupon -> cupon.getEstado() == EstadoCupon.DISPONIBLE
                        && cupon.getFechaVencimiento().isAfter(LocalDateTime.now()))
                .map(cupon -> new ItemCuponDTO(
                        cupon.getId(),
                        cupon.getCodigo(),
                        cupon.getNombre(),
                        cupon.getDescuento(),
                        cupon.getFechaVencimiento(),
                        cupon.getTipo()
                )).collect(Collectors.toList());
    }

    @Override
    public List<ItemCuponDTO> listarCuponesCliente(ListarCupoDTO listarCupoDTO) {
        return cuponRepo.findAll().stream()
                .filter(cupon -> cupon.getEstado() == EstadoCupon.DISPONIBLE
                        && cupon.getFechaVencimiento().isAfter(LocalDateTime.now())
                        && (cupon.getTipo() == TipoCupon.UNICO
                        || (cupon.getTipo() == TipoCupon.INDIVIDUAL && cupon.getBeneficiarios().contains(listarCupoDTO.idCliente()))))
                .map(cupon -> new ItemCuponDTO(
                        cupon.getId(),
                        cupon.getCodigo(),
                        cupon.getNombre(),
                        cupon.getDescuento(),
                        cupon.getFechaVencimiento(),
                        cupon.getTipo()
                )).collect(Collectors.toList());
    }

    private String generarCodigoIndividual() {
        return "CUPON-" + UUID.randomUUID().toString();
    }



    private boolean existeCodigo(String codigo) {
        return cuponRepo.findByCodigo(codigo).isPresent();
    }
    private boolean existeNombre(String nombre) {
        return cuponRepo.findByNombre(nombre).isPresent();
    }

}
