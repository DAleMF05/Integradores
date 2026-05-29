package org.example.mcsvinscripciones.service;

import org.example.mcsvinscripciones.clients.CarreraClientRest;
import org.example.mcsvinscripciones.clients.EstudianteClientRest;
import org.example.mcsvinscripciones.dto.InscripcionDTO;
import org.example.mcsvinscripciones.entities.Inscripcion;
import org.example.mcsvinscripciones.models.CarreraM;
import org.example.mcsvinscripciones.models.EstudianteM;
import org.example.mcsvinscripciones.repositories.IInscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InscripcionService implements IInscripcionService {

    @Autowired
    private IInscripcionRepository inscripcionRepository;

    @Autowired
    private EstudianteClientRest estudianteC;

    @Autowired
    private CarreraClientRest carreraC;

    @Transactional(readOnly = true)
    public List<InscripcionDTO> getAll() {
        return this.inscripcionRepository.findAll().stream().map(InscripcionDTO::new).toList();
    }

    @Override
    @Transactional
    public InscripcionDTO save(InscripcionDTO inscripcionDTO) {

        EstudianteM estudiante = estudianteC.findByDni(inscripcionDTO.getDniEstudiante());
        CarreraM carrera = carreraC.getNombre(inscripcionDTO.getNombreCarrera());

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
