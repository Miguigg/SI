package com.proyecto_si.pr_si.entidades;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

@Entity
public class ParteSiniestro implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numPasajeros;

    @Min(1)
    private Integer numVehiculos;
    // 	@Pattern(regexp="\\(\\d{3}\\)\\d{3}-\\d{4}")
    @Pattern(regexp="^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")
    private String horaFirmaParte;

    @OneToMany
    @JoinColumn(name = "ParteId")
    private List<Vehiculo> vehiculo;

    @OneToMany
    @JoinColumn(name = "ParteId")
    private List<Conductor> conductor;

    public ParteSiniestro(){}

    public ParteSiniestro(Integer numPasajeros, Integer numVehiculos, String horaFirmaParte, List<Vehiculo> vehiculo, List<Conductor> conductor){
        this.numPasajeros = numPasajeros;
        this.numVehiculos = numVehiculos;
        this.horaFirmaParte = horaFirmaParte;
        this.vehiculo = vehiculo;
        this.conductor = conductor;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Integer getNumPasajeros(){
        return numPasajeros;
    }

    public void setNumPasajeros(Integer numPasajeros){
        this.numPasajeros =  numPasajeros;
    }

    public Integer getNumVehiculos(){
        return numVehiculos;
    }

    public void setNumVehiculos(Integer numVehiculos){
        this.numVehiculos = numVehiculos;
    }

    public String getHoraFirmaParte(){
        return horaFirmaParte;
    }

    public void setHoraFirmaParte(String horaFirmaParte){
        this.horaFirmaParte = horaFirmaParte;
    }

    public List<Vehiculo> getVehiculo(){
        return this.vehiculo;
    }

    public void setVehiculo(List<Vehiculo> vehiculo){
        this.vehiculo = vehiculo;
    }

    public void addVehiculo(Vehiculo v){
        this.vehiculo.add(v);
    }

    public List<Conductor> getConductor(){
        return this.conductor;
    }

    public void setConductor(List<Conductor> conductor){
        this.conductor = conductor;
    }

    public void addConductor(Conductor c){
        this.conductor.add(c);
    }

    @Override
    public int hashCode() {
        if (this.id != null) {
            return this.id.hashCode();
        }
        int hash = 5;
        hash = 40 * hash + Objects.hashCode(this.numPasajeros);
        hash = 40 * hash + Objects.hashCode(this.numVehiculos);
        hash = 40 * hash + Objects.hashCode(this.horaFirmaParte);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ParteSiniestro other = (ParteSiniestro) obj;
        if (this.id != null) {
            return this.id.equals(other.getId());
        }        
        if (!Objects.equals(this.numPasajeros, other.numPasajeros)) {
            return false;
        }
        if (!Objects.equals(this.numVehiculos, other.numVehiculos)) {
            return false;
        }
        if (!Objects.equals(this.horaFirmaParte, other.horaFirmaParte)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Parte del Siniestro{" + "Hora de la Firma=" + horaFirmaParte + ", numero pasajeros=" + numPasajeros + ", numero vehiculos=" + numVehiculos + '}';
    }

}