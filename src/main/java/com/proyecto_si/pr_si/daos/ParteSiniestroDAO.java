package com.proyecto_si.pr_si.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto_si.pr_si.entidades.ParteSiniestro;

public interface ParteSiniestroDAO extends JpaRepository<ParteSiniestro, Long>{
    
    public List<ParteSiniestro> findByNumPasajeros(Integer numPasajeros);

    public List<ParteSiniestro> findByNumVehiculos(Integer numVehiculos);

}
