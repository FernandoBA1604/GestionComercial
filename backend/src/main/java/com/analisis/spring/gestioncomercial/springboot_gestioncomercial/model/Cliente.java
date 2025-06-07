package com.analisis.spring.gestioncomercial.springboot_gestioncomercial.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Cliente")
public class Cliente {

    @Id
    @Column(name = "CODPERSONA")
    private String codPersona;

    @Column(name = "NRORUC")
    private String nroRuc;

    @Column(name = "VIGENTE")
    private String vigente = "sí";

    public Cliente() {
        // Genera UUID solo si codPersona es null
        if (this.codPersona == null) {
            this.codPersona = UUID.randomUUID().toString();
        }
    }

    public Cliente(String nroRuc) {
        this.codPersona = UUID.randomUUID().toString();
        this.nroRuc = nroRuc;
        this.vigente = "sí";
    }

    // Getters y Setters
    public String getCodPersona() {
        return codPersona;
    }

    public void setCodPersona(String codPersona) {
        this.codPersona = codPersona;
    }

    public String getNroRuc() {
        return nroRuc;
    }

    public void setNroRuc(String nroRuc) {
        this.nroRuc = nroRuc;
    }

    public String getVigente() {
        return vigente;
    }

    public void setVigente(String vigente) {
        this.vigente = vigente;
    }
}
