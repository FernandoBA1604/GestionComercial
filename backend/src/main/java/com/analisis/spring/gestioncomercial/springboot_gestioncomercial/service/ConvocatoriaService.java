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

    /**
     * Verifica si existe una convocatoria con el id dado.
     */
    public boolean existeConvocatoria(String idConvocatoria) {
        return convocatoriaRepository.existsById(idConvocatoria);
    }

    /**
     * Guarda la lista de convocatorias directamente en la tabla Convocatoria (singular).
     * Método simple para guardar sin procesar.
     */
    @Transactional
    public void guardarConvocatorias(List<Convocatoria> convocatorias) {
        convocatoriaRepository.saveAll(convocatorias);
    }

    /**
     * Método principal que procesa la lista, guarda en Convocatoria (singular), 
     * crea Cliente y ObjetoContratacion si no existen, y copia a Convocatorias (plural) sin FK asignados.
     */
    @Transactional
    public void procesarYGuardarConvocatorias(List<Convocatoria> convocatorias) {
    for (Convocatoria convocatoria : convocatorias) {
        convocatoriaRepository.save(convocatoria);

        String nombreEntidad = convocatoria.getNombreEntidad();
        Cliente cliente = clienteRepository.findByNroRuc(nombreEntidad);
        if (cliente == null) {
            cliente = new Cliente(nombreEntidad); // constructor genera UUID y asigna vigente
            clienteRepository.save(cliente);
        }

        String nombreObjeto = convocatoria.getObjetoContratacion();
        ObjetoContratacion objeto = objetoContratacionRepository.findByDescObj(nombreObjeto);
        if (objeto == null) {
            objeto = new ObjetoContratacion(nombreObjeto); // constructor genera UUID y asigna vigente
            objetoContratacionRepository.save(objeto);
        }

        Convocatorias convFinal = new Convocatorias();

        convFinal.setAlcance(convocatoria.getAlcance());
        convFinal.setCantidad(convocatoria.getCantidad());
        convFinal.setDescripcion(convocatoria.getDescripcion());
        convFinal.setFechaConvocatoria(convocatoria.getFechaConvocatoria());
        convFinal.setFechaPublicacion(convocatoria.getFechaPublicacion());
        convFinal.setTipoSeleccion(convocatoria.getTipoSeleccion());
        convFinal.setPlazoDias(convocatoria.getPlazoDias());
        // Si id es autogenerado, no asignar manualmente:
        // convFinal.setIdConvocatoria(convocatoria.getIdConvocatoria());

        convFinal.setCliente(null);
        convFinal.setObjetoContratacion(null);

        convocatoriasRepository.save(convFinal);

        System.out.println("Guardando Convocatoria: " + convocatoria.getIdConvocatoria());
System.out.println("Cliente: " + cliente.getNroRuc() + " ID: " + cliente.getCodPersona());
System.out.println("Objeto: " + objeto.getDescObj() + " ID: " + objeto.getCodObj());
System.out.println("Guardando Convocatorias (plural)");

    }
    }


}
