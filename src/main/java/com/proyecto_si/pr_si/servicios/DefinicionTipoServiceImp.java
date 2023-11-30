package com.proyecto_si.pr_si.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto_si.pr_si.daos.DefinicionTipoDAO;
import com.proyecto_si.pr_si.entidades.DefinicionTipo;

@Service
public class DefinicionTipoServiceImp implements DefinicionTipoService{

    @Autowired
    DefinicionTipoDAO definicionTipoDAO;

    @Override
	@Transactional
    public Optional<DefinicionTipo> findById(Long id){//
        return definicionTipoDAO.findById(id);
    }

    @Override
	@Transactional
    public DefinicionTipo crear(DefinicionTipo definicionTipo){//
        return definicionTipoDAO.save(definicionTipo);
    }

    @Override
	@Transactional
    public DefinicionTipo modificar(DefinicionTipo definicionTipo){//
        return definicionTipoDAO.save(definicionTipo);
    }

    @Override
	@Transactional
    public void eliminar(DefinicionTipo definicionTipo){//
        definicionTipoDAO.delete(definicionTipo);
    }

    @Override
	@Transactional
    public List<DefinicionTipo> findByPatronDescripcionCondicion (String def){//
        return definicionTipoDAO.findByPatronDescripcionCondicion(def);
    }

}
