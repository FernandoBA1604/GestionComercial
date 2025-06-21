package com.analisis.spring.gestioncomercial.springboot_gestioncomercial.service;

import com.analisis.spring.gestioncomercial.springboot_gestioncomercial.model.Cliente;
import com.analisis.spring.gestioncomercial.springboot_gestioncomercial.model.Convocatoria;
import com.analisis.spring.gestioncomercial.springboot_gestioncomercial.model.Convocatorias;
import com.analisis.spring.gestioncomercial.springboot_gestioncomercial.model.ObjetoContratacion;
import com.analisis.spring.gestioncomercial.springboot_gestioncomercial.repository.ClienteRepository;
import com.analisis.spring.gestioncomercial.springboot_gestioncomercial.repository.ConvocatoriaRepository;
import com.analisis.spring.gestioncomercial.springboot_gestioncomercial.repository.ConvocatoriasRepository;
import com.analisis.spring.gestioncomercial.springboot_gestioncomercial.repository.ObjetoContratacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConvocatoriaService {

    @Autowired
    private ConvocatoriaRepository convocatoriaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ObjetoContratacionRepository objetoContratacionRepository;

    @Autowired
    private ConvocatoriasRepository convocatoriasRepository;

    public boolean existeConvocatoria(String idConvocatoria) {
        return convocatoriaRepository.existsById(idConvocatoria);
    }

    public boolean existeConvocatorias(String idConvocatoria) {
        return convocatoriasRepository.existsById(idConvocatoria);
    }

    @Transactional
    public void guardarConvocatorias(List<Convocatoria> convocatoria) {
        convocatoriaRepository.saveAll(convocatoria);
    }

    @Transactional
    public void guardarConvocatorias2(List<Convocatorias> convocatorias) {
        convocatoriasRepository.saveAll(convocatorias);
    }

    @Transactional
    public void guardarClientes(List<Cliente> clientes) {
        clienteRepository.saveAll(clientes);
    }

    @Transactional
    public void guardarObjetosContratacion(List<ObjetoContratacion> objetoscontratacion) {
        objetoContratacionRepository.saveAll(objetoscontratacion);
    }

}
