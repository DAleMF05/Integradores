package org.example.mcsvestudiantes.service;

import org.example.mcsvestudiantes.dto.EstudianteDTO;
import org.example.mcsvestudiantes.entities.Estudiante;
import org.example.mcsvestudiantes.repository.IEstudianteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstudianteService implements IEstudianteService {

    @Autowired
    private IEstudianteRepository estudianteRepository;

    @Override
    @Transactional
    public EstudianteDTO save(EstudianteDTO e) {
        final var estudiante = new Estudiante(e);
        final var result = this.estudianteRepository.save(estudiante);
        return new EstudianteDTO(result);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstudianteDTO> getAll() { return this.estudianteRepository.getAll(); }


    @Override
//    @Transactional(readOnly = true)
    public EstudianteDTO getById(Long id) {
        return this.estudianteRepository.findById(id).map(EstudianteDTO::new).orElseThrow( () -> new RuntimeException("Estudiante no encontrado"));
    }

    @Override
    @Transactional(readOnly = true)
    public EstudianteDTO getByLibretaUni(String lu) {
        return this.estudianteRepository.findByNumLibretaUni(lu).map(EstudianteDTO::new).orElseThrow( () -> new RuntimeException("Estudiante no encontrado con esa LU"));
    }

    @Override
    public List<EstudianteDTO> getByGenero(char gen) {
        return this.estudianteRepository.getByGenero(gen);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstudianteDTO> getByCarreraAndCiudad(String carrera, String ciudad) {
        return this.estudianteRepository.getEstudiantesByCarreraAndCiudad(carrera, ciudad);
    }


}
