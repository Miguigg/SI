package com.proyecto_si.pr_si.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


/*
 * Queda en duda como funciona la fecha 
 * 
 */

@Entity
public class Accidente implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(10)
    private double costeEstimado;
    
    @NotBlank
    private String descripcionGravedad;

    @Size(min = 1, max = 3)
    private String periodo;

    @Temporal(TemporalType.DATE)
    private Date fechaAccidente;

    @OneToOne
    ParteSiniestro parteSiniestro;
    

    //Definicion N:M con su tabla intermedia
    @ManyToMany(cascade = CascadeType.ALL)
    List<DefinicionTipo> definicionTipo;

     @JoinTable(
        name = "rel_accidente_definicionTipo",
        joinColumns = @JoinColumn(name = "id", nullable = false),
        inverseJoinColumns = @JoinColumn(name="id", nullable = false)
    )    
    
    @OneToMany
    @JoinColumn(name = "AccidenteId")
    List<Condiciones> condiciones;

    public Accidente (){}

    public Accidente(double costeEstimado, String descripcionGravedad, Date fechaAccidente, String periodo,
     List<DefinicionTipo> definicionTipo, ParteSiniestro parteSiniestro, List<Condiciones> condiciones){
        this.costeEstimado = costeEstimado;
        this.descripcionGravedad = descripcionGravedad;
        this.fechaAccidente = fechaAccidente;
        this.periodo = periodo;
        this.definicionTipo = definicionTipo;
        this.parteSiniestro = parteSiniestro;
        this.condiciones = condiciones;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCosteEstimado() {
        return this.costeEstimado;
    }

    public void setCosteEstimado(double costeEstimado) {
        this.costeEstimado = costeEstimado;
    }

    public String getDescripcionGravedad() {
        return this.descripcionGravedad;
    }

    public void setDescripcionGravedad(String descripcionGravedad) {
        this.descripcionGravedad = descripcionGravedad;
    }

    public Date getFechaAccidente() {
        return this.fechaAccidente;
    }

    public void setFechaAccidente(Date fechaAccidente) {
        this.fechaAccidente = fechaAccidente;
    }

    public String getPeriodo(){
        return this.periodo;
    }

    public void setPeriodo(String periodo){
        this.periodo = periodo;
    }

    public List<DefinicionTipo> getDefinicionTipo(){
        return this.definicionTipo;
    }

    public void setDefinicionTipo(List<DefinicionTipo> definicionTipo){
        this.definicionTipo = definicionTipo;
    }

    public void addDefinicionTipo(DefinicionTipo definicionTipo){
        this.definicionTipo.add(definicionTipo);
    }

    public ParteSiniestro getParteSiniestro(){
        return this.parteSiniestro;
    }

    public void setParteSiniestro(ParteSiniestro parteSiniestro){
        this.parteSiniestro = parteSiniestro;
    }

    public List<Condiciones> getCondiciones(){
        return this.condiciones;
    }

    public void setCondiciones(List<Condiciones> condiciones){
        this.condiciones = condiciones;
    }

    public void addCondiciones(Condiciones condiciones){
        this.condiciones.add(condiciones);
    }

    public int hashCode() {
        if (this.id != null) {
            return this.id.hashCode();
        }
        
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.descripcionGravedad);
        hash = 23 * hash + Objects.hashCode(this.periodo);
        hash = (int) (23 * hash + this.costeEstimado);
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
        final Accidente other = (Accidente) obj;
        if (this.id != null) {
            return this.id.equals(other.getId());
        }
        if (!Objects.equals(this.descripcionGravedad, other.descripcionGravedad)) {
            return false;
        }
        if (!Objects.equals(this.fechaAccidente, other.fechaAccidente)) {
            return false;
        }
        if (!Objects.equals(this.periodo, other.periodo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Accidente{" + "Coste Estimado=" + costeEstimado + ", Fecha accidente=" + fechaAccidente  + '}';
    }

}
