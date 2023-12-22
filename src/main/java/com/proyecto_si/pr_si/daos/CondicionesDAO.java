package com.proyecto_si.pr_si.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto_si.pr_si.entidades.Condiciones;
import java.util.List;


public interface CondicionesDAO extends JpaRepository<Condiciones, Long>{
    
    @Query("SELECT c FROM Condiciones c WHERE c.descripcionCondicion LIKE %?1%")
	public List<Condiciones> findByPatronDescripcionCondicion(String patron);

    public List<Condiciones> findByNombreCondicion(String nombreCondicion);
}
