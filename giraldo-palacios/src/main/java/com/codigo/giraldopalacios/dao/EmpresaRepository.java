package com.codigo.giraldopalacios.dao;

import com.codigo.giraldopalacios.entidad.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> {
}
