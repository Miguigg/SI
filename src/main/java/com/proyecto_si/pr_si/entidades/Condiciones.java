package com.proyecto_si.pr_si.entidades;
import java.util.Objects;
import com.proyecto_si.pr_si.entidades.Enumerados.TipoCondicion;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Condiciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoCondicion tipoCondicion;
    
    @NotEmpty
    private String nombreCondicion;
    
    @NotEmpty
    private String descripcionCondicion;

    public Condiciones(){}

    public Condiciones(String nombreCondicion, String descripcionCondicion){
        this.nombreCondicion = nombreCondicion;
        this.descripcionCondicion = descripcionCondicion;
        this.tipoCondicion = TipoCondicion.NO_DEFINIDO;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoCondicion getTipoCondicion() {
        return this.tipoCondicion;
    }

    public void setTipoCondicion(TipoCondicion tipoCondicion) {
        this.tipoCondicion = tipoCondicion;
    }

    public String getNombreCondicion() {
        return this.nombreCondicion;
    }

    public void setNombreCondicion(String nombreCondicion) {
        this.nombreCondicion = nombreCondicion;
    }

    public String getDescripcionCondicion() {
        return this.descripcionCondicion;
    }

    public void setDescripcionCondicion(String descripcionCondicion) {
        this.descripcionCondicion = descripcionCondicion;
    }

    @Override
    public int hashCode() {
        if (this.id != null) {
            return this.id.hashCode();
        }
        int hash = 5;
        hash = 35 * hash + Objects.hashCode(this.descripcionCondicion);
        hash = 35 * hash + Objects.hashCode(this.nombreCondicion);
        hash = 35 * hash + Objects.hashCode(this.tipoCondicion);

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
        final Condiciones other = (Condiciones) obj;
        
        if (!Objects.equals(this.descripcionCondicion, other.descripcionCondicion)) {
            return false;
        }
        
        if (!Objects.equals(this.nombreCondicion, other.nombreCondicion)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Condiciones {" + "Tipo condicion=" + tipoCondicion + ", Descripcion=" + descripcionCondicion  + '}';
    }

}
