package com.analisis.spring.gestioncomercial.springboot_gestioncomercial.service;

import com.analisis.spring.gestioncomercial.springboot_gestioncomercial.model.Convocatoria;
import com.analisis.spring.gestioncomercial.springboot_gestioncomercial.repository.ConvocatoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvocatoriaService {

    @Autowired
    private ConvocatoriaRepository convocatoriaRepository;

    public void guardarConvocatorias(List<Convocatoria> lista) {
        convocatoriaRepository.saveAll(lista);
    }

    public boolean existeConvocatoria(String idConvocatoria) {
        return convocatoriaRepository.existsById(idConvocatoria);
    }

    public List<Convocatoria> obtenerTodas() {
        return convocatoriaRepository.findAll();
    }
}
