package com.example.integrador3.service;

import com.example.integrador3.dto.CarreraDTO;
import com.example.integrador3.dto.CarreraInsDTO;
import com.example.integrador3.dto.ReporteCarreraDTO;
import com.example.integrador3.entities.Carrera;

import java.util.List;

public interface ICarreraService {

    CarreraDTO save(CarreraDTO carrera);

    List<CarreraDTO> getAll();

    CarreraDTO getById(Long id);

    List<CarreraInsDTO> buscarInscriptos();

    List<ReporteCarreraDTO> generarReporte();
}
