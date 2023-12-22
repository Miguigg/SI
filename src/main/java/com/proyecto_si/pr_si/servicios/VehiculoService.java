package com.proyecto_si.pr_si.servicios;

import java.util.Date;
import java.util.List;
import com.proyecto_si.pr_si.entidades.Vehiculo;

public interface VehiculoService {
    public Vehiculo crear (Vehiculo vehiculo);
    public Vehiculo modificar (Vehiculo vehiculo);
    public void eliminar (Vehiculo vehiculo);
    public List<Vehiculo> buscarPorMatricula (String licencePlate);
    public List<Vehiculo> buscarPorDefecto (String defectoVehiculo);
    public List<Vehiculo> buscarFechaMatriculacion (Date fechaMatriculacion);
    public List<Vehiculo> buscarPorPatroDefecto (String patron);
}
