package com.example.integrador3.service;

import com.example.integrador3.repository.IEstudianteRepository;
import com.example.integrador3.repository.IInscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstudianteService implements IEstudianteService {

    @Autowired
    private IEstudianteRepository estudianteRepository;
}
