package com.analisis.spring.gestioncomercial.springboot_gestioncomercial.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "CLIENTE")
public class Cliente {

    @Id
    @Column(name = "CODPERSONA", length = 8, nullable = false)
    private String codPersona;

    @Column(name = "NOMBREENTIDAD", length = 100)
    private String nombreEntidad;

    public Cliente() {
        this.codPersona = UUID.randomUUID().toString().substring(0, 8); // Tomar solo los primeros 8 caracteres
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

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombre) {
        this.nombreEntidad = nombre;
    }
}
