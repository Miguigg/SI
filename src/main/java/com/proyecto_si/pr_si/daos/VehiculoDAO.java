package com.proyecto_si.pr_si.daos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto_si.pr_si.entidades.Vehiculo;

import java.util.Date;
import java.util.List;



public interface VehiculoDAO extends JpaRepository<Vehiculo, String>{

    public Vehiculo findBylicencePlate(String licencePlate);

    public List<Vehiculo> findBydefectoVehiculo(String defectoVehiculo);

    public List<Vehiculo> findByfechaMatriculacion(Date fechaMatriculacion);

    @Query("SELECT licencePlate FROM Vehiculo v WHERE v.defectoVehiculo LIKE %?1%")
	public List<Vehiculo> findByPatrondefectoVehiculo(String patron);
}
