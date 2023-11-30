package com.proyecto_si.pr_si.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto_si.pr_si.daos.ParteSiniestroDAO;
import com.proyecto_si.pr_si.entidades.ParteSiniestro;


@Service
public class ParteSiniestroServiceImp implements ParteSiniestroService {
    @Autowired
    ParteSiniestroDAO parteSiniestroDAO;


    @Override
	@Transactional(readOnly = true)
    public Optional<ParteSiniestro> buscarPorId(Long id){//
        return parteSiniestroDAO.findById(id);
    }
    
    @Override
	@Transactional
    public ParteSiniestro crear(ParteSiniestro p){//
        return parteSiniestroDAO.save(p);
    }

    @Override
	@Transactional
    public ParteSiniestro modificar(ParteSiniestro parteSiniestro){//
        return parteSiniestroDAO.save(parteSiniestro);
    }

    @Override
	@Transactional
    public void eliminar(ParteSiniestro parteSiniestro){//
        parteSiniestroDAO.delete(parteSiniestro);
    }

    @Override
	@Transactional
    public List<ParteSiniestro> findByNumPasajeros (int numPasajeros){//
        return parteSiniestroDAO.findByNumPasajeros(numPasajeros);
    }

    @Override
	@Transactional
    public List<ParteSiniestro> findByNumVehiculos (int numVehiculos){//
        return parteSiniestroDAO.findByNumVehiculos(numVehiculos);
    }
}
