package com.proyecto_si.pr_si.servicios;

import java.util.List;
import java.util.Optional;

import com.proyecto_si.pr_si.entidades.DefinicionTipo;

public interface DefinicionTipoService {
    public DefinicionTipo crear (DefinicionTipo definicionTipo);
    public DefinicionTipo modificar (DefinicionTipo definicionTipo);
    public void eliminar (DefinicionTipo definicionTipo);
    public List<DefinicionTipo> findByPatronDescripcionCondicion(String def);
    public Optional<DefinicionTipo> findById(Long id);
}
