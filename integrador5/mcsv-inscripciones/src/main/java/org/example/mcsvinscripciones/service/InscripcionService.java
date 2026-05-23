package org.example.mcsvinscripciones.service;

import org.example.mcsvinscripciones.dto.InscripcionDTO;
import org.example.mcsvinscripciones.entities.Inscripcion;
import org.example.mcsvinscripciones.repositories.IInscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InscripcionService {

    @Autowired
    private IInscripcionRepository inscripcionRepository;


    @Transactional(readOnly = true)
    public List<InscripcionDTO> getAll() {
        return this.inscripcionRepository.getAll();
    }

//    @Override
//    @Transactional
//    public InscripcionDTO save(InscripcionDTO inscripcionDTO) {
//        Estudiante estudiante = estudianteRepository.findByDni(inscripcionDTO.getDniEstudiante())
//                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
//
//        Carrera carrera = carreraRepository.findByNombre(inscripcionDTO.getNombreCarrera())
//                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));
//
//        Inscripcion inscripcion = new Inscripcion(
//                inscripcionDTO.getFechaInsc(),
//                inscripcionDTO.getFechaGrad(),
//                inscripcionDTO.getAntiguedad(),
//                estudiante,
//                carrera
//        );
//
//        Inscripcion savedInscripcion = inscripcionRepository.save(inscripcion);
//        return new InscripcionDTO(savedInscripcion);
//    }
}
