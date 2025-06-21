package com.analisis.spring.gestioncomercial.springboot_gestioncomercial.model;

public class ParticipanteDTO {

    private String ruc;
    private Integer puntajeTecnico;
    private Integer puntajeEconomico;
    private Integer puntajeTotal;

    // Constructor
    public ParticipanteDTO(String ruc, Integer puntajeTecnico, Integer puntajeEconomico) {
        this.ruc = ruc;
        this.puntajeTecnico = puntajeTecnico;
        this.puntajeEconomico = puntajeEconomico;
        this.puntajeTotal = puntajeTecnico + puntajeEconomico;  // Calcular puntaje total
    }

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

    public Integer getPuntajeTotal() {
        return puntajeTotal;
    }

    public void setPuntajeTotal(Integer puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
    }
}
