package com.proyecto_si.pr_si.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto_si.pr_si.entidades.Conductor;
import java.util.List;


public interface ConductorDAO extends JpaRepository<Conductor, String>{
    
    List<Conductor> findByNombre(String nombre);

    List<Conductor> findByDNI(String DNI);

    List<Conductor> findByAnhoExp(Integer anhoExp);

}
