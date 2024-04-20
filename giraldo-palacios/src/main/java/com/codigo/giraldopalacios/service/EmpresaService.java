package com.codigo.giraldopalacios.service;

import com.codigo.giraldopalacios.entidad.EmpresaEntity;
import com.codigo.giraldopalacios.request.EmpresaRequest;

import java.util.List;
import java.util.Optional;

public interface EmpresaService {
    EmpresaEntity crear(EmpresaRequest EmpresaRequest);
    Optional<EmpresaEntity> buscarPorId(Long id);
    List<EmpresaEntity> buscarTodos();
    EmpresaEntity actualizar(Long id, EmpresaRequest EmpresaRequest);
    EmpresaEntity borrar(Long id);

}
