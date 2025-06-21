package com.analisis.spring.gestioncomercial.springboot_gestioncomercial.controller;

import com.analisis.spring.gestioncomercial.springboot_gestioncomercial.repository.ConvocatoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/convocatorias")
@CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500"})
public class ConvocatoriasController {

    @Autowired
    private ConvocatoriasRepository convocatoriasRepository;

@GetMapping("/existe/{idConvocatoria}")
public ResponseEntity<Map<String, Boolean>> verificarConvocatoriaExistente(@PathVariable String idConvocatoria) {
    System.out.println("Consultando existencia de convocatoria con ID: " + idConvocatoria);

    // No es necesario rellenar con espacios, ya que idConvocatoria es VARCHAR
    // Se utiliza el valor tal cual se recibe desde el frontend
    boolean exists = convocatoriasRepository.existsById(idConvocatoria);
    System.out.println("Resultado de existencia en la base de datos: " + exists);  // Verifica el resultado de la consulta

    Map<String, Boolean> response = new HashMap<>();
    response.put("exists", exists);
    return ResponseEntity.ok(response);  // Devolvemos la respuesta correcta
}




}
