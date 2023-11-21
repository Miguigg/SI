package com.proyecto_si.pr_si.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto_si.pr_si.entidades.Accidente;

public interface AccidenteDAO extends JpaRepository<Accidente, Long>{
    
    @Query("SELECT fechaAccidente FROM Accidente a WHERE a.descripcionGravedad LIKE %?1%")
	public List<Accidente> findByPatronDescripcionGravedad(String patron);

    public List<Accidente> findByCosteEstimado(double costeEstimado);
}
