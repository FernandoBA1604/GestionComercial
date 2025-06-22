package com.analisis.spring.gestioncomercial.springboot_gestioncomercial.service;

import com.analisis.spring.gestioncomercial.springboot_gestioncomercial.model.Convocatorias;
import com.analisis.spring.gestioncomercial.springboot_gestioncomercial.model.Participante;
import com.analisis.spring.gestioncomercial.springboot_gestioncomercial.model.ParticipanteDTO;
import com.analisis.spring.gestioncomercial.springboot_gestioncomercial.repository.ParticipanteRepository;
import com.analisis.spring.gestioncomercial.springboot_gestioncomercial.repository.ConvocatoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParticipanteService {

    @Autowired
    private ParticipanteRepository participanteRepository;

    @Autowired
    private ConvocatoriasRepository convocatoriasRepository;

    // Validar si la convocatoria existe
    public boolean validarConvocatoriaExistente(String idConvocatoria) {
        return convocatoriasRepository.existsById(idConvocatoria);
    }

    // Crear participante después de validar la convocatoria
    // Servicio de guardar participante con validación de existencia de la convocatoria
    @Transactional
    public Participante guardarParticipante(Participante participante) {
        // Verifica que la convocatoria exista en la base de datos
        Optional<Convocatorias> convocatoriaOptional = convocatoriasRepository.findById(participante.getConvocatoria().getIdConvocatoria());
    
        // Si la convocatoria no existe, lanzar una excepción con un mensaje claro
        if (!convocatoriaOptional.isPresent()) {
            throw new RuntimeException("La convocatoria con ID " + participante.getConvocatoria().getIdConvocatoria() + " no existe.");
        }

        // Si la convocatoria existe, asigna el objeto Convocatorias al Participante
        Convocatorias convocatoria = convocatoriaOptional.get();
        participante.setConvocatoria(convocatoria);

        // Guarda el participante en la base de datos
        return participanteRepository.save(participante);
    }

    // Eliminar participante
    public void eliminarParticipante(String ruc) {
        participanteRepository.deleteById(ruc);
    }

    // Obtener participante por RUC
    public Optional<Participante> obtenerParticipantePorRuc(String ruc) {
        return participanteRepository.findById(ruc);
    }

    // Obtener participantes por ID de Convocatoria y devolver DTO
    public List<ParticipanteDTO> obtenerParticipantesPorConvocatoria(String idConvocatoria) {
        // Recuperar la lista de participantes de la base de datos
        List<Participante> participantes = participanteRepository.findByConvocatoriaIdConvocatoria(idConvocatoria);

        // Convertir la lista de Participante en una lista de ParticipanteDTO
        List<ParticipanteDTO> participanteDTOList = new ArrayList<>();

        for (Participante participante : participantes) {
            // Convertir cada Participante en ParticipanteDTO
            participanteDTOList.add(new ParticipanteDTO(
                participante.getIdParticipante(),  
                participante.getNombreParticipante(),
                participante.getPuntajeTecnico(),
                participante.getPuntajeEconomico()
            ));
        }

        return participanteDTOList;
    }

}
