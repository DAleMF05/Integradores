package com.example.integrador3.service;

import com.example.integrador3.dto.InscripcionDTO;
import com.example.integrador3.entities.Carrera;
import com.example.integrador3.entities.Estudiante;
import com.example.integrador3.entities.Inscripcion;
import com.example.integrador3.repository.ICarreraRepository;
import com.example.integrador3.repository.IEstudianteRepository;
import com.example.integrador3.repository.IInscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InscripcionService implements IInscripcionService {

    @Autowired
    private IInscripcionRepository inscripcionRepository;
    @Autowired
    private IEstudianteRepository estudianteRepository;
    @Autowired
    private ICarreraRepository carreraRepository;

    @Transactional(readOnly = true)
    public List<InscripcionDTO> getAll() {
        return this.inscripcionRepository.getAll();
    }

    @Override
    @Transactional
    public InscripcionDTO save(InscripcionDTO inscripcionDTO) {
        Estudiante estudiante = estudianteRepository.findByDni(inscripcionDTO.getDniEstudiante())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Carrera carrera = carreraRepository.findByNombre(inscripcionDTO.getNombreCarrera())
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        Inscripcion inscripcion = new Inscripcion(
            inscripcionDTO.getFechaInsc(),
            inscripcionDTO.getFechaGrad(),
            inscripcionDTO.getAntiguedad(),
            estudiante,
            carrera
        );

        Inscripcion savedInscripcion = inscripcionRepository.save(inscripcion);
        return new InscripcionDTO(savedInscripcion);
    }

}
