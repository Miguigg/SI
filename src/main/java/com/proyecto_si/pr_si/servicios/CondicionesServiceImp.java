package com.proyecto_si.pr_si.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto_si.pr_si.daos.CondicionesDAO;
import com.proyecto_si.pr_si.entidades.Condiciones;

@Service
public class CondicionesServiceImp implements CondicionesService  {
    @Autowired
    CondicionesDAO condicionesDAO;

    @Override
	@Transactional
    public Optional<Condiciones> buscarPorId(Long id){//
        return condicionesDAO.findById(id);
    }

    @Override
	@Transactional
    public Condiciones crear(Condiciones condiciones){//
        return condicionesDAO.save(condiciones);
    }

    @Override
	@Transactional
    public Condiciones modificar(Condiciones condiciones){//
        return condicionesDAO.save(condiciones);
    }

    @Override
	@Transactional
    public void eliminar(Condiciones condiciones){//
        condicionesDAO.delete(condiciones);
    }

    @Override
	@Transactional
    public List<Condiciones> findByNombreCondicion (String nombre){//
        return condicionesDAO.findByNombreCondicion(nombre);
    }
}
