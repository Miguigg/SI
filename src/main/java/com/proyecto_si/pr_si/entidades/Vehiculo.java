package com.proyecto_si.pr_si.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.proyecto_si.pr_si.entidades.Enumerados.TipoVehiculo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Temporal;

@Entity
public class Vehiculo implements Serializable{

    @Id
    @NotNull
    private String licencePlate;

    private String defectoVehiculo;

    @Enumerated(EnumType.STRING)
    private TipoVehiculo tipoVehiculo;

    @Temporal(TemporalType.DATE)
    private Date fechaMatriculacion;

    public Vehiculo(){};

    public Vehiculo(String licencePlate, String defectoVehiculo, Date fechaMatriculacion){
        this.defectoVehiculo = defectoVehiculo;
        this.licencePlate = licencePlate;
        this.fechaMatriculacion = fechaMatriculacion;//aqui se le mete la fecha asi o por parametro? porque no entiendo lo que hace en Pedido.java https://github.com/esei-si-dagss/pedidos-persistencia-23/blob/main/src/main/java/es/uvigo/mei/pedidos/entidades/Pedido.java
        this.tipoVehiculo = TipoVehiculo.SIN_DEFINIR; //Esto como se define luego?
    }

    public String getlicencePlate() {
        return this.licencePlate;
    }

    public void setlicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getdefectoVehiculo() {
        return this.defectoVehiculo;
    }

    public void setdefectoVehiculo(String defectoVehiculo) {
        this.defectoVehiculo = defectoVehiculo;
    }

    public TipoVehiculo gettipoVehiculo() {
        return this.tipoVehiculo;
    }

    public void settipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public Date getfechaMatriculacion() {
        return this.fechaMatriculacion;
    }

    public void setfechaMatriculacion(Date fechaMatriculacion) {
        this.fechaMatriculacion = fechaMatriculacion;
    }

    @Override
    public int hashCode() {
        int hash = Objects.hashCode(this.licencePlate);
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
        final Vehiculo other = (Vehiculo) obj;       
        if (!Objects.equals(this.licencePlate, other.licencePlate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "Matricula=" + licencePlate + ", Fecha matricula=" + fechaMatriculacion  + '}';
    }

}
