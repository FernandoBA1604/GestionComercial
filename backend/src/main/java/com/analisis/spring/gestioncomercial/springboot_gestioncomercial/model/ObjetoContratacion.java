package com.analisis.spring.gestioncomercial.springboot_gestioncomercial.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "OBJETOCONTRATACION")
public class ObjetoContratacion {

    @Id
    @Column(name = "CODOBJETO")
    private String codObj;  // cambiado para coincidir con setCodObj en servicio

    @Column(name = "DESCOBJ")
    private String descObj;

    @Column(name = "VIGENTE")
    private String vigente = "sí";

    public ObjetoContratacion() {
        if (this.codObj == null) {
            this.codObj = UUID.randomUUID().toString();
        }
    }

    public ObjetoContratacion(String descObj) {
        this.codObj = UUID.randomUUID().toString();
        this.descObj = descObj;
        this.vigente = "sí";
    }

    // Getters y Setters
    public String getCodObj() {
        return codObj;
    }

    public void setCodObj(String codObj) {
        this.codObj = codObj;
    }

    public String getDescObj() {
        return descObj;
    }

    public void setDescObj(String descObj) {
        this.descObj = descObj;
    }

    public String getVigente() {
        return vigente;
    }

    public void setVigente(String vigente) {
        this.vigente = vigente;
    }
}
