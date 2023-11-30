package com.proyecto_si.pr_si.servicios;

import java.util.List;
import java.util.Optional;

import com.proyecto_si.pr_si.entidades.ParteSiniestro;

public interface ParteSiniestroService {
    public ParteSiniestro crear (ParteSiniestro parteSiniestro);
    public ParteSiniestro modificar (ParteSiniestro parteSiniestro);
    public void eliminar (ParteSiniestro parteSiniestro);
    public List<ParteSiniestro> findByNumPasajeros (int numPasajeros);
    public List<ParteSiniestro> findByNumVehiculos (int numVehiculos);
    public Optional<ParteSiniestro> buscarPorId(Long id);
}
