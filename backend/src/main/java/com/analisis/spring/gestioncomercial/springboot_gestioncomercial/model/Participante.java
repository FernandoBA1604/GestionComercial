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
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrementable
    @Column(name = "IDPARTICIPANTE", nullable = false)
    private Long idParticipante;  

    @Column(name = "NOMBREPARTICIPANTE", length = 75, nullable = false)
    private String nombreParticipante;

    @Column(name = "PUNTAJETECNICO", nullable = false)
    private Integer puntajeTecnico;

    @Column(name = "PUNTAJEECONOMICO", nullable = false)
    private Integer puntajeEconomico;

    @Transient
    private Integer puntajeTotal;

    // Constructor vacío
    public Participante() {}

    // Getter para puntaje total calculado
    public Integer getPuntajeTotal() {
        return puntajeTecnico + puntajeEconomico;
    }

    // Getters y Setters
    public Long getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(Long idParticipante) {
        this.idParticipante = idParticipante;
    }

    public String getNombreParticipante() {
        return nombreParticipante;
    }

    public void setNombreParticipante(String nombreParticipante) {
        this.nombreParticipante = nombreParticipante;
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
