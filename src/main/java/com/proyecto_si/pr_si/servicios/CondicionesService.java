package com.proyecto_si.pr_si.servicios;

import java.util.List;
import java.util.Optional;

import com.proyecto_si.pr_si.entidades.Condiciones;

public interface CondicionesService {
    public Condiciones crear (Condiciones condiciones);
    public Condiciones modificar (Condiciones condiciones);
    public void eliminar (Condiciones condiciones);
    public Optional<Condiciones> buscarPorId (Long id);
    public List<Condiciones> findByNombreCondicion (String nombre);
}
