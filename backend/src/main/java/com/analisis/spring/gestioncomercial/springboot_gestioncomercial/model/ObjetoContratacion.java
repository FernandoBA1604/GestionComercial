package com.analisis.spring.gestioncomercial.springboot_gestioncomercial.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ObjetoContratacion")
public class ObjetoContratacion {

    @Id
    @Column(name = "CODOBJETO", length = 8, nullable = false)
    private String codObj;  // cambiado para coincidir con setCodObj en servicio

    @Column(name = "NOMOBJ", length = 100)
    private String nomObj;

    @Column(name = "VIGENTE", length = 1)
    private String vigente;

    public ObjetoContratacion() {
        this.codObj = UUID.randomUUID().toString().substring(0, 8); // Tomar solo los primeros 8 caracteres
        this.vigente = "S";
    }

    /*public ObjetoContratacion(String nomObj) {
        this.codObj = UUID.randomUUID().toString().substring(0, 8); // Tomar solo los primeros 8 caracteres
        this.nomObj = nomObj;
        this.vigente = "S";
    }*/

    // Getters y Setters
    public String getCodObj() {
        return codObj;
    }

    public void setCodObj(String codObj) {
        this.codObj = codObj;
    }

    public String getNomObj() {
        return nomObj;
    }

    public void setNomObj(String nomObj) {
        this.nomObj = nomObj;
    }

    public String getVigente() {
        return vigente;
    }

    public void setVigente(String vigente) {
        this.vigente = vigente;
    }
}
