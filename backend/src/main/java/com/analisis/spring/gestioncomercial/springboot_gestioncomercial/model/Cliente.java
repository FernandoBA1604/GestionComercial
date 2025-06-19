package com.analisis.spring.gestioncomercial.springboot_gestioncomercial.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Cliente")
public class Cliente {

    @Id
    @Column(name = "CODPERSONA", length = 8, nullable = false)
    private String codPersona;

    @Column(name = "NRORUC", length = 100)
    private String nroRuc;

    @Column(name = "VIGENTE", length = 1)
    private String vigente;

    public Cliente() {
        this.codPersona = UUID.randomUUID().toString().substring(0, 8); // Tomar solo los primeros 8 caracteres
        this.vigente = "S";
    }

    /*public Cliente(String nroRuc) {
        this.codPersona = UUID.randomUUID().toString().substring(0, 8); // Tomar solo los primeros 8 caracteres
        this.nroRuc = nroRuc;
        this.vigente = "S";
    }*/

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
