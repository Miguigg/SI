package com.proyecto_si.pr_si.entidades;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

@Entity
public class ParteSiniestro implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numPasajeros;

    @Min(1)
    private int numVehiculos;
    // 	@Pattern(regexp="\\(\\d{3}\\)\\d{3}-\\d{4}")
    @Pattern(regexp="^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")
    private String horaFirmaParte;

    @OneToMany
    private Vehiculo vehiculo;

    @OneToMany
    private Conductor conductor;

    public ParteSiniestro(){}

    public ParteSiniestro(int numPasajeros, int numVehiculos, String horaFirmaParte,Vehiculo vehiculo, Conductor conductor){
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

    public int getNumPasajeros(){
        return numPasajeros;
    }

    public void setNumPasajeros(int numPasajeros){
        this.numPasajeros =  numPasajeros;
    }

    public int getNumVehiculos(){
        return numVehiculos;
    }

    public void setNumVehiculos(int numVehiculos){
        this.numVehiculos = numVehiculos;
    }

    public String getHoraFirmaParte(){
        return horaFirmaParte;
    }

    public void setHoraFirmaParte(String horaFirmaParte){
        this.horaFirmaParte = horaFirmaParte;
    }

    public Vehiculo getVehiculo(){
        return this.vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo){
        this.vehiculo = vehiculo;
    }

    public Conductor getConductor(){
        return this.conductor;
    }

    public void setConductor(Conductor conductor){
        this.conductor = conductor;
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