package org.example.mcsvcarreras.service;

import org.example.mcsvcarreras.dto.CarreraDTO;
import org.example.mcsvcarreras.dto.CarreraInsDTO;
import org.example.mcsvcarreras.dto.ReporteCarreraDTO;

import java.util.List;

public interface ICarreraService {

    CarreraDTO save(CarreraDTO carrera);

    List<CarreraDTO> getAll();

    CarreraDTO getById(String id);

    CarreraDTO findByNombre(String nombre);

}
