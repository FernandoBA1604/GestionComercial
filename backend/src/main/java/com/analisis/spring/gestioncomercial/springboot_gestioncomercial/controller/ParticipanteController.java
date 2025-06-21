package com.analisis.spring.gestioncomercial.springboot_gestioncomercial.controller;

import com.analisis.spring.gestioncomercial.springboot_gestioncomercial.model.Convocatorias;
import com.analisis.spring.gestioncomercial.springboot_gestioncomercial.model.Participante;
import com.analisis.spring.gestioncomercial.springboot_gestioncomercial.model.ParticipanteDTO;
import com.analisis.spring.gestioncomercial.springboot_gestioncomercial.repository.ConvocatoriasRepository;
import com.analisis.spring.gestioncomercial.springboot_gestioncomercial.service.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/participantes")
@CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500"})
public class ParticipanteController {

    @Autowired
    private ParticipanteService participanteService;

    @Autowired
    private ConvocatoriasRepository convocatoriasRepository;

    // Crear participante
    // Crear participante
    @PostMapping("/crear")
    public ResponseEntity<Participante> crearParticipante(@RequestBody Participante participante) {
        System.out.println("Recibiendo participante: " + participante);
        System.out.println("ID de convocatoria: " + participante.getConvocatoria().getIdConvocatoria());

        // Verificar que el idConvocatoria no sea nulo
        if (participante.getConvocatoria() == null || participante.getConvocatoria().getIdConvocatoria() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // ID de convocatoria no proporcionado
        }

        // Buscar la convocatoria por id
        Optional<Convocatorias> convocatoria = convocatoriasRepository.findById(participante.getConvocatoria().getIdConvocatoria());
        if (!convocatoria.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // La convocatoria no existe
        }

        // Asociar la convocatoria al participante
        participante.setConvocatoria(convocatoria.get());

        // Guardar el participante
        try {
            Participante nuevoParticipante = participanteService.guardarParticipante(participante);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoParticipante); // Retornar con código 201 (CREATED)
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Error al guardar el participante
        }
    }

    // Obtener participantes por ID de Convocatoria (usando DTO)
    @GetMapping("/convocatoria/{idConvocatoria}")
    public ResponseEntity<List<ParticipanteDTO>> obtenerParticipantesPorConvocatoria(@PathVariable String idConvocatoria) {
        // Obtener la lista de Participantes y convertir a DTO
        List<ParticipanteDTO> participantesDTO = participanteService.obtenerParticipantesPorConvocatoria(idConvocatoria);

        if (participantesDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Si no hay participantes
        }

        // Devolver la lista de DTOs
        return ResponseEntity.ok(participantesDTO); 
    }

    // Eliminar participante
    @DeleteMapping("/eliminar/{ruc}")
    public ResponseEntity<String> eliminarParticipante(@PathVariable String ruc) {
        participanteService.eliminarParticipante(ruc);
        return ResponseEntity.ok("Participante eliminado exitosamente");
    }

}
