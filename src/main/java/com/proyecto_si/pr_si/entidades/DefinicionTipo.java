package com.proyecto_si.pr_si.entidades;

import java.util.List;
import java.util.Objects;

import com.proyecto_si.pr_si.entidades.Enumerados.TipoAccidente;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;

/*
 * Queda en duda como funciona el enumerado
 * 
 */

@Entity
public class DefinicionTipo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoAccidente tipoAccidente; 

    @NotEmpty
    private String descripcion;


    @ManyToMany(mappedBy = "id")
    List<Accidente> accidente;

    public DefinicionTipo (){}

    public DefinicionTipo(String descripcion){
        this.descripcion = descripcion;
        this.tipoAccidente = TipoAccidente.NO_DEFINIDO;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoAccidente getTipoAccidente() {
        return this.tipoAccidente;
    }

    public void setTipoAccidente(TipoAccidente tipoAccidente) {
        this.tipoAccidente = tipoAccidente;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Accidente> getAccidentes(){
        return this.accidente;
    } 

    public void setAccidente(List<Accidente> accidentes){
        this.accidente = accidentes;
    }

    @Override
    public int hashCode() {
        if (this.id != null) {
            return this.id.hashCode();
        }
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.descripcion);
        hash = 31 * hash + Objects.hashCode(this.tipoAccidente);

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
        final DefinicionTipo other = (DefinicionTipo) obj;
        
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }      
        if (!Objects.equals(this.tipoAccidente, other.tipoAccidente)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tipo de accidente{" + "Tipo=" + tipoAccidente + ", =" + tipoAccidente  + '}';
    }

}
