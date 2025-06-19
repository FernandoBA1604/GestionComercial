package com.analisis.spring.gestioncomercial.springboot_gestioncomercial.repository;

import com.analisis.spring.gestioncomercial.springboot_gestioncomercial.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
}
