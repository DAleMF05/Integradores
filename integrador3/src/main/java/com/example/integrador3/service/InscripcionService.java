package com.example.integrador3.service;

import com.example.integrador3.dto.EstudianteDTO;
import com.example.integrador3.dto.InscripcionDTO;
import com.example.integrador3.repository.IInscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InscripcionService implements IInscripcionService {

    @Autowired
    private IInscripcionRepository inscripcionRepository;

    @Transactional(readOnly = true)
    public List<InscripcionDTO> getAll() {
        return this.inscripcionRepository.getAll();
    }
}
