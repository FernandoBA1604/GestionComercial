package com.analisis.spring.gestioncomercial.springboot_gestioncomercial.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "PARTICIPANTE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class Participante {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDCONVOCATORIA", referencedColumnName = "IDCONVOCATORIA", nullable = false)
    private Convocatorias convocatoria;

    @Id
    @Column(name = "RUC", length = 15, nullable = false)
    private String ruc;

    @Column(name = "PUNTAJETECNICO", nullable = false)
    private Integer puntajeTecnico;

    @Column(name = "PUNTAJEECONOMICO", nullable = false)
    private Integer puntajeEconomico;

    @Transient
    private Integer puntajeTotal;

    // Constructor vacío
    public Participante() {}

    // Getters y Setters
    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public Integer getPuntajeTecnico() {
        return puntajeTecnico;
    }

    public void setPuntajeTecnico(Integer puntajeTecnico) {
        this.puntajeTecnico = puntajeTecnico;
    }

    public Integer getPuntajeEconomico() {
        return puntajeEconomico;
    }

    public void setPuntajeEconomico(Integer puntajeEconomico) {
        this.puntajeEconomico = puntajeEconomico;
    }

    public Convocatorias getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(Convocatorias convocatoria) {
        this.convocatoria = convocatoria;
    }
}
