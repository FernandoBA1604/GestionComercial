package com.analisis.spring.gestioncomercial.springboot_gestioncomercial.repository;

import com.analisis.spring.gestioncomercial.springboot_gestioncomercial.model.Participante;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipanteRepository extends JpaRepository<Participante, String> {
    public int countByConvocatoriaIdConvocatoria(String idConvocatoria);
    public List<Participante> findByConvocatoriaIdConvocatoria(String idConvocatoria);
}
