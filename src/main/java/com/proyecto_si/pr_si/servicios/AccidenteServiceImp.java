package com.proyecto_si.pr_si.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto_si.pr_si.daos.AccidenteDAO;
import com.proyecto_si.pr_si.entidades.Accidente;

@Service
public class AccidenteServiceImp implements AccidenteService{
    @Autowired
    AccidenteDAO accidenteDAO;

    @Override
	@Transactional(readOnly = true)
    public Optional<Accidente> buscarPorId(Long id){//
        return accidenteDAO.findById(id);
    }

    @Override
	@Transactional
    public Accidente crear(Accidente accidente){//
        return accidenteDAO.save(accidente);
    }

    @Override
	@Transactional
    public Accidente modificar(Accidente accidente){//
        return accidenteDAO.save(accidente);
    }

    @Override
	@Transactional
    public void eliminar(Accidente accidente){//
        accidenteDAO.delete(accidente);
    }

    @Override
	@Transactional
    public List<Accidente> findByPatronDescripcionGravedad (String patron){//
        return accidenteDAO.findByPatronDescripcionGravedad(patron);
    }

    @Override
	@Transactional
    public List<Accidente> findByCosteEstimado (Double costeEstimado){//
        return accidenteDAO.findByCosteEstimado(costeEstimado);
    }
}
