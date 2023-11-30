package com.proyecto_si.pr_si.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto_si.pr_si.entidades.DefinicionTipo;

public interface DefinicionTipoDAO extends JpaRepository<DefinicionTipo, Long>{
    
    @Query("SELECT descripcion FROM DefinicionTipo d WHERE d.descripcion LIKE %?1%")
	public List<DefinicionTipo> findByPatronDescripcionCondicion(String patron);
}
