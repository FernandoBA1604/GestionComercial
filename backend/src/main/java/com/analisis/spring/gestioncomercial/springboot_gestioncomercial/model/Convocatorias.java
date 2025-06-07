package com.analisis.spring.gestioncomercial.springboot_gestioncomercial.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Convocatorias", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"IDCONVOCATORIA"})
})
public class Convocatorias {

    @Id
    @Column(name = "IDCONVOCATORIA", length = 8, nullable = false)
    private String idConvocatoria;

    // Relaciones FK con Cliente y ObjetoContratacion
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODPERSONA", referencedColumnName = "CODPERSONA")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODOBJETO", referencedColumnName = "CODOBJETO")
    private ObjetoContratacion objetoContratacion;

    // Copia de atributos sin relación (sin prioridad)
    @Column(name = "NOMBREENTIDAD", length = 100)
    private String nombreEntidad;

    @Column(name = "FECHAPUBLICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPublicacion;

    @Column(name = "TIPOSELECCION", length = 1000)
    private String tipoSeleccion;

    @Column(name = "DESCRIPCION", length = 1000)
    private String descripcion;

    @Column(name = "ALCANCE", length = 1000)
    private String alcance;

    @Column(name = "CANTIDAD")
    private Integer cantidad;

    @Column(name = "PLAZODIAS")
    private Integer plazoDias;

    @Column(name = "FECHACONVOCATORIA")
    @Temporal(TemporalType.DATE)
    private Date fechaConvocatoria;

    // Constructor vacío
    public Convocatorias() {}

    // Getters y Setters

    public String getIdConvocatoria() {
        return idConvocatoria;
    }

    public void setIdConvocatoria(String idConvocatoria) {
        this.idConvocatoria = idConvocatoria;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ObjetoContratacion getObjetoContratacion() {
        return objetoContratacion;
    }

    public void setObjetoContratacion(ObjetoContratacion objetoContratacion) {
        this.objetoContratacion = objetoContratacion;
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
}
