package com.codigo.giraldopalacios.service.impl;

import com.codigo.giraldopalacios.constants.Constants;
import com.codigo.giraldopalacios.dao.EmpresaRepository;
import com.codigo.giraldopalacios.entidad.EmpresaEntity;
import com.codigo.giraldopalacios.request.EmpresaRequest;
import com.codigo.giraldopalacios.service.EmpresaService;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.scanner.Constant;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;
    @Override
    public EmpresaEntity crear(EmpresaRequest empresaRequest) {
        EmpresaEntity empresaEntity = new EmpresaEntity();
        empresaEntity = getEmpresaRequest(empresaEntity, empresaRequest);

        return empresaRepository.save(empresaEntity);
    }

    private EmpresaEntity getEmpresaRequest(EmpresaEntity empresaEntity, EmpresaRequest empresaRequest){
        empresaEntity.setRazonSocial(empresaRequest.getRazonSocial());
        empresaEntity.setTipoDocumento(empresaRequest.getTipoDocumento());
        empresaEntity.setNumeroDocumento(empresaRequest.getNumeroDocumento());
        empresaEntity.setCondicion(empresaRequest.getCondicion());
        empresaEntity.setDireccion(empresaRequest.getDireccion());
        empresaEntity.setDistrito(empresaRequest.getDistrito());
        empresaEntity.setProvincia(empresaRequest.getProvincia());
        empresaEntity.setDepartamento(empresaRequest.getDepartamento());
        empresaEntity.setEsAgenteRetencion(empresaRequest.getEsAgenteRetencion());
        empresaEntity.setEstado(Constants.STATUS_ACTIVE);
        empresaEntity.setUsuCrea(Constants.USU_ADMIN);
        empresaEntity.setDateCrea(getTimestamp());

        return empresaEntity;
    }
    private Timestamp getTimestamp(){
        long currenTIme = System.currentTimeMillis();
        return new Timestamp(currenTIme);
    }

    @Override
    public Optional<EmpresaEntity> buscarPorId(Long id) {
        return empresaRepository.findById(id);
    }

    @Override
    public List<EmpresaEntity> buscarTodos() {
        return empresaRepository.findAll();
    }

    @Override
    public EmpresaEntity actualizar(Long id, EmpresaRequest empresaRequest) {
        Optional<EmpresaEntity> optional = empresaRepository.findById(id);
        if(optional.isPresent()){
            EmpresaEntity empresaEntity = optional.get();
            empresaEntity = getEmpresaRequest(empresaEntity, empresaRequest);
            empresaEntity.setUsuModif(Constants.USU_ADMIN);
            empresaEntity.setDateModif(getTimestamp());

            return empresaRepository.save(empresaEntity);
        }

        return null;
    }

    @Override
    public EmpresaEntity borrar(Long id) {
        Optional<EmpresaEntity> empresaRecuperada = empresaRepository.findById(id);
        if(empresaRecuperada.isPresent()){
            empresaRecuperada.get().setEstado(Constants.STATUS_INACTIVE);
            empresaRecuperada.get().setUsuDelet(Constants.USU_ADMIN);
            empresaRecuperada.get().setDateDelet(getTimestamp());

            return empresaRepository.save(empresaRecuperada.get());
        }

        return null;
    }
}
