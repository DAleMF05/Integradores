package com.example.integrador3.service;

import com.example.integrador3.dto.EstudianteDTO;
import com.example.integrador3.entities.Estudiante;
import com.example.integrador3.repository.IEstudianteRepository;
import com.example.integrador3.repository.IInscripcionRepository;
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
    public List<EstudianteDTO> getAll() {
        return this.estudianteRepository.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public EstudianteDTO getById(Long id) {
        return this.estudianteRepository.findById(id).map(EstudianteDTO::new).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }

    @Override
    @Transactional(readOnly = true)
    public EstudianteDTO getByLibretaUni(String lu) {
        return this.estudianteRepository.findByNumLibretaUni(lu).map(EstudianteDTO::new).orElseThrow(() -> new RuntimeException("Estudiante no encontrado con esa LU"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstudianteDTO> getByGenero(char gen) {
        return this.estudianteRepository.getByGenero(gen);
    }


}
