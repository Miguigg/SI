package com.proyecto_si.pr_si.entidades;

import java.io.Serializable;
import java.util.Objects;

import com.proyecto_si.pr_si.entidades.Enumerados.NivelEducativo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/*
 * Queda en duda como funciona el enumerado
 * 
 */


@Entity
public class Conductor implements Serializable{
    @Id
    @NotNull
    private String DNI;

    @Positive
    private int anhoExp;
    
    @NotEmpty
    private String nombre;
    
    @NotEmpty
    private String sexo;

    @Enumerated(EnumType.STRING)
    private NivelEducativo nivelEducativo;


    @ManyToOne
    private ParteSiniestro parteSiniestro;

    public Conductor(){
        /*En Pedido lo rellena? */
    }

    public Conductor(String DNI, int anhoExp, String nombre, String sexo, ParteSiniestro parteSiniestro){
        this.DNI = DNI;
        this.anhoExp = anhoExp;
        this.nombre = nombre;
        this.sexo = sexo;
        this.parteSiniestro = parteSiniestro;
        this.nivelEducativo = NivelEducativo.SIN_ESTABLECER;//como se define luego?
    }

    public String getDNI() {
        return this.DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public int getAnhoExp() {
        return this.anhoExp;
    }

    public void setAnhoExp(int anhoExp) {
        this.anhoExp = anhoExp;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return this.sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public NivelEducativo getNivelEducativo() {
        return this.nivelEducativo;
    }

    public void setNivelEducativo(NivelEducativo nivelEducativo) {
        this.nivelEducativo = nivelEducativo;
    }

    public ParteSiniestro getParteSiniestro() {
        return this.parteSiniestro;
    }

    public void setParteSiniestro(ParteSiniestro parteSiniestro) {
        this.parteSiniestro = parteSiniestro;
    }


    @Override
    public int hashCode() {
        int hash = Objects.hashCode(this.DNI);
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
        final Conductor other = (Conductor) obj;
        
        if (!Objects.equals(this.parteSiniestro, other.parteSiniestro)) {
            return false;
        }
        
        if (!Objects.equals(this.DNI, other.DNI)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Conductor{" + "DNI=" + DNI + ", nombre=" + nombre  + '}';
    }

}
