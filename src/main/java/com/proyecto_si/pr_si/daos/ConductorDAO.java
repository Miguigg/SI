package com.proyecto_si.pr_si.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto_si.pr_si.entidades.Conductor;
import java.util.List;
import java.util.Optional;


public interface ConductorDAO extends JpaRepository<Conductor, Long>{
    
    List<Conductor> findByNombre(String nombre);

    Optional<Conductor> findByDNI(String DNI);

    List<Conductor> findByAnhoExp(Integer anhoExp);

}
