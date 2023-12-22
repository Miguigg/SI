package com.proyecto_si.pr_si.servicios;

import java.util.List;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto_si.pr_si.daos.VehiculoDAO;
import com.proyecto_si.pr_si.entidades.Vehiculo;

import org.springframework.transaction.annotation.Transactional;


@Service
public class VehiculoServiceImp implements VehiculoService{
    @Autowired
    VehiculoDAO vehiculoDAO;

    @Override
	@Transactional
    public Vehiculo crear(Vehiculo v){
        return vehiculoDAO.save(v);
    }

    @Override
	@Transactional
    public Vehiculo modificar(Vehiculo v){
        return vehiculoDAO.save(v);
    }

    @Override
	@Transactional
    public void eliminar(Vehiculo v){
        vehiculoDAO.delete(v);
    }

    @Override
	@Transactional(readOnly = true)
    public List<Vehiculo> buscarPorMatricula (String licencePlate){
        return vehiculoDAO.findByLicencePlate(licencePlate);
    }

    @Override
	@Transactional(readOnly = true)
    public List<Vehiculo> buscarPorDefecto (String defectoVehiculo){
        return vehiculoDAO.findByDefectoVehiculo(defectoVehiculo);
    }

    @Override
	@Transactional(readOnly = true)
    public List<Vehiculo> buscarFechaMatriculacion (Date fechaMatriculacion){
        return vehiculoDAO.findByFechaMatriculacion(fechaMatriculacion);
    }

    @Override
	@Transactional(readOnly = true)
    public List<Vehiculo> buscarPorPatroDefecto (String patron){
        return vehiculoDAO.findByPatrondefectoVehiculo(patron);
    }
}
