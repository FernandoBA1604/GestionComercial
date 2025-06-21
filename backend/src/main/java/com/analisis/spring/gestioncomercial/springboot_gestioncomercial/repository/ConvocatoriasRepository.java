package com.analisis.spring.gestioncomercial.springboot_gestioncomercial.repository;

import com.analisis.spring.gestioncomercial.springboot_gestioncomercial.model.Convocatorias;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConvocatoriasRepository extends JpaRepository<Convocatorias, String> {
    Optional<Convocatorias> findByIdConvocatoria(String idConvocatoria);
}
