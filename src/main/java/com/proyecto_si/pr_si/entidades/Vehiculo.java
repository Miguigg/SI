package com.proyecto_si.pr_si.entidades;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import com.proyecto_si.pr_si.entidades.Enumerados.TipoVehiculo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Temporal;

/*
 * Queda en duda como funciona la fecha y el enumerado
 * Duda en el equals, Si hay una PK como la matricula, hace falta comparar todos los atr
 */

@Entity
public class Vehiculo implements Serializable{

    @Id
    @NotNull
    private String licence_plate;

    private String defecto_vehiculo;

    @Enumerated(EnumType.STRING)
    private TipoVehiculo tipo_vehiculo;

    @Temporal(TemporalType.DATE)
    private Date fecha_matriculacion;

    @ManyToOne
    private ParteSiniestro parteSiniestro;

    public Vehiculo(){};

    public Vehiculo(String licence_plate, String defecto_vehiculo, Date fecha_matriculacion , ParteSiniestro parteSiniestro){
        this.defecto_vehiculo = defecto_vehiculo;
        this.licence_plate = licence_plate;
        this.fecha_matriculacion = fecha_matriculacion;//aqui se le mete la fecha asi o por parametro? porque no entiendo lo que hace en Pedido.java https://github.com/esei-si-dagss/pedidos-persistencia-23/blob/main/src/main/java/es/uvigo/mei/pedidos/entidades/Pedido.java
        this.parteSiniestro = parteSiniestro;
        this.tipo_vehiculo = TipoVehiculo.SIN_DEFINIR; //Esto como se define luego?
    }

    public String getLicence_plate() {
        return this.licence_plate;
    }

    public void setLicence_plate(String licence_plate) {
        this.licence_plate = licence_plate;
    }

    public String getDefecto_vehiculo() {
        return this.defecto_vehiculo;
    }

    public void setDefecto_vehiculo(String defecto_vehiculo) {
        this.defecto_vehiculo = defecto_vehiculo;
    }

    public TipoVehiculo getTipo_vehiculo() {
        return this.tipo_vehiculo;
    }

    public void setTipo_vehiculo(TipoVehiculo tipo_vehiculo) {
        this.tipo_vehiculo = tipo_vehiculo;
    }

    public Date getFecha_matriculacion() {
        return this.fecha_matriculacion;
    }

    public void setFecha_matriculacion(Date fecha_matriculacion) {
        this.fecha_matriculacion = fecha_matriculacion;
    }

    public ParteSiniestro getParteSiniestro() {
        return this.parteSiniestro;
    }

    public void setParteSiniestro(ParteSiniestro parteSiniestro) {
        this.parteSiniestro = parteSiniestro;
    }

    @Override
    public int hashCode() {
        int hash = Objects.hashCode(this.licence_plate);
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
        /*Esto seria necesario teniendo la matricula? */
        if (!Objects.equals(this.parteSiniestro, other.parteSiniestro)) {
            return false;
        }
        
        if (!Objects.equals(this.licence_plate, other.licence_plate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "Matricula=" + licence_plate + ", Fecha matricula=" + fecha_matriculacion  + '}';
    }

}
