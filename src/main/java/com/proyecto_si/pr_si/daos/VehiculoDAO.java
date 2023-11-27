package com.proyecto_si.pr_si.daos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto_si.pr_si.entidades.Vehiculo;

import java.util.Date;
import java.util.List;



public interface VehiculoDAO extends JpaRepository<Vehiculo, String>{

    public List<Vehiculo> findByLicencePlate(String licencePlate);

    public List<Vehiculo> findByDefectoVehiculo(String defectoVehiculo);

    public List<Vehiculo> findByFechaMatriculacion(Date fechaMatriculacion);

    @Query("SELECT v.licencePlate FROM Vehiculo v WHERE v.defectoVehiculo LIKE %?1%")
	public List<String> findByPatrondefectoVehiculo(String patron);
}
