package com.analisis.spring.gestioncomercial.springboot_gestioncomercial.model;

public class ParticipanteDTO {

    private Long idParticipante;  // ID del participante
    private String nombreParticipante;
    private Integer puntajeTecnico;
    private Integer puntajeEconomico;

    // Constructor
    public ParticipanteDTO(Long idParticipante, String nombreParticipante, Integer puntajeTecnico, Integer puntajeEconomico) {
        this.idParticipante = idParticipante;
        this.nombreParticipante = nombreParticipante;
        this.puntajeTecnico = puntajeTecnico;
        this.puntajeEconomico = puntajeEconomico;
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

    // Calcula el puntaje total basado en los puntajes técnico y económico
    public Integer getPuntajeTotal() {
        return puntajeTecnico + puntajeEconomico;
    }
}
