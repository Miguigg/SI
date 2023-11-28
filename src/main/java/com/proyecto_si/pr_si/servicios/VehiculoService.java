package com.proyecto_si.pr_si.servicios;

import com.proyecto_si.pr_si.entidades.Conductor;
import com.proyecto_si.pr_si.entidades.Vehiculo;

public interface VehiculoService {
    public Conductor crear (Vehiculo vehiculo);
    public Conductor modificar (Vehiculo vehiculo);
    public void eliminar (Vehiculo vehiculo);
}
