package com.proyecto_si.pr_si.servicios;

import java.util.List;
import java.util.Optional;

import com.proyecto_si.pr_si.entidades.Conductor;

public interface ConductorService {
    public Conductor crear (Conductor conductor);
    public Conductor modificar (Conductor conductor);
    public void eliminar (Conductor conductor);
    public Optional<Conductor> buscarPorDNI (String dni);
    public List<Conductor> buscarPorAnho (int anhoExp);
    public List<Conductor> buscarTodos ();
}
