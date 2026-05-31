package org.example.mcsvestudiantes.service;

import org.example.mcsvestudiantes.dto.EstudianteDTO;

import java.util.List;

public interface IEstudianteService {

    EstudianteDTO save(EstudianteDTO estudiante);

    List<EstudianteDTO> getAll();

    EstudianteDTO getById(Long id);

    EstudianteDTO getByLibretaUni(String lu);

    List<EstudianteDTO> getByGenero(char gen);

//    List<EstudianteDTO> getByCarreraAndCiudad(String carrera, String ciudad);

    EstudianteDTO findByDni(String dni);

}
