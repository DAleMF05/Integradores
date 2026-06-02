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

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        Inscripcion inscripcion = new Inscripcion(
                inscripcionDTO.getFechaInsc(),
                inscripcionDTO.getFechaGrad(),
                inscripcionDTO.getAntiguedad(),
                inscripcionDTO.getIdCarrera(),
                inscripcionDTO.getDniEstudiante()

        );
        Inscripcion savedInscripcion = inscripcionRepository.save(inscripcion);
        return new InscripcionDTO(savedInscripcion);
    }

    @Override
    @Transactional
    public List<EstudianteM> getByCarreraYCiudad(String nombreCarrera, String ciudad) {

        CarreraM carrera = carreraC.findByNombre(nombreCarrera);
        if (carrera == null) {
            return Collections.emptyList(); // Si la carrera no existe, no hay estudiantes
        }

        //Buscamos en nuestra DB local (MySQL) todas las inscripciones de esa carrera
        List<Inscripcion> inscripciones = inscripcionRepository.findByIdCarrera(carrera.getIdCarrera());

        if (inscripciones.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> dniEstudiantes = inscripciones.stream()
                .map(Inscripcion::getDniEstudiante)
                .collect(Collectors.toList());

        //Le mandamos la lista de IDs y la ciudad al mcsv-estudiantes para que los filtre en Postgres
        List<EstudianteM> estudiantesFiltrados = estudianteC.getByDniYCiudad(dniEstudiantes, ciudad);

        return estudiantesFiltrados;
    }
}
