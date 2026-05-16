package com.example.integrador3.service;

import com.example.integrador3.dto.EstudianteDTO;

import java.util.List;

public interface IEstudianteService {


    EstudianteDTO save(EstudianteDTO estudiante);

    List<EstudianteDTO> getAll();

    EstudianteDTO getById(Long id);

    EstudianteDTO getByLibretaUni(String lu);

    List<EstudianteDTO> getByGenero(char gen);
}
