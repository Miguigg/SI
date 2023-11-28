package com.proyecto_si.pr_si.servicios;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.proyecto_si.pr_si.daos.ConductorDAO;
import com.proyecto_si.pr_si.entidades.Conductor;

import org.springframework.transaction.annotation.Transactional;

public class ConductorServiceImp implements ConductorService{
    @Autowired
    ConductorDAO conductorDAO;

    @Override
	@Transactional
    public Conductor crear(Conductor conductor){
        return conductorDAO.save(conductor);
    }

    @Override
	@Transactional
    public Conductor modificar(Conductor conductor){
        return conductorDAO.save(conductor);
    }

    @Override
	@Transactional
    public void eliminar(Conductor conductor){
        conductorDAO.delete(conductor);
    }

    @Override
	@Transactional(readOnly = true)
	public Optional<Conductor> buscarPorDNI(String dni) {
		return conductorDAO.findById(dni);
	}

    @Override
	@Transactional(readOnly = true)
	public List<Conductor> buscarPorAnho(int anhoExp) {
		return conductorDAO.findByAnhoExp(anhoExp);
	}

}
