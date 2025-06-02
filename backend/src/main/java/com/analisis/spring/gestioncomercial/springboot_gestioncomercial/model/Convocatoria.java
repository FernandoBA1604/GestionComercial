package com.analisis.spring.gestioncomercial.springboot_gestioncomercial.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Convocatoria")
public class Convocatoria {

    @Id
    @Column(name = "idConvocatoria", length = 8, nullable = false)
    private String idConvocatoria;

    @Column(name = "nombreEntidad", length = 100)
    private String nombreEntidad;

    @Column(name = "fechaPublicacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPublicacion;

    @Column(name = "tipoSeleccion", length = 1000)
    private String tipoSeleccion;

    @Column(name = "objetoContratacion", length = 100)
    private String objetoContratacion;

    @Column(name = "descripcion", length = 1000)
    private String descripcion;

    @Column(name = "alcance", length = 1000)
    private String alcance;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "plazoDias")
    private Integer plazoDias;

    @Column(name = "fechaConvocatoria")
    @Temporal(TemporalType.DATE)
    private Date fechaConvocatoria;

    @Column(name = "prioridad")
    private Integer prioridad;

    // Getters y Setters

    public String getIdConvocatoria() {
        return idConvocatoria;
    }

    public void setIdConvocatoria(String idConvocatoria) {
        this.idConvocatoria = idConvocatoria;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getTipoSeleccion() {
        return tipoSeleccion;
    }

    public void setTipoSeleccion(String tipoSeleccion) {
        this.tipoSeleccion = tipoSeleccion;
    }

    public String getObjetoContratacion() {
        return objetoContratacion;
    }

    public void setObjetoContratacion(String objetoContratacion) {
        this.objetoContratacion = objetoContratacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAlcance() {
        return alcance;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getPlazoDias() {
        return plazoDias;
    }

    public void setPlazoDias(Integer plazoDias) {
        this.plazoDias = plazoDias;
    }

    public Date getFechaConvocatoria() {
        return fechaConvocatoria;
    }

    public void setFechaConvocatoria(Date fechaConvocatoria) {
        this.fechaConvocatoria = fechaConvocatoria;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }
}