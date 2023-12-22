package com.proyecto_si.pr_si.servicios;

import java.util.List;
import java.util.Optional;

import com.proyecto_si.pr_si.entidades.Accidente;

public interface  AccidenteService {
    public Accidente crear (Accidente accidente);
    public Accidente modificar (Accidente accidente);
    public void eliminar (Accidente accidente);
    public Optional<Accidente> buscarPorId (Long id);
    public List<Accidente> findByPatronDescripcionGravedad(String patron);
    public List<Accidente> findByCosteEstimado(Double costeEstimado);
    public List<Accidente> buscarTodos();
}
