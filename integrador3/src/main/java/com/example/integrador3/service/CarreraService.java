package com.example.integrador3.service;

import com.example.integrador3.dto.CarreraDTO;
import com.example.integrador3.entities.Carrera;
import com.example.integrador3.repository.ICarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarreraService implements ICarreraService {

    @Autowired
    private ICarreraRepository carreraRepository;

    @Override
    public CarreraDTO saveCarrera(CarreraDTO carrera) {
        return null;
    }

    /*
    @Override
    public CarreraDTO saveCarrera(CarreraDTO carre) {
        final var carrera = new Carrera(carre);
        final var result = this.carreraRepository.save(carrera);
    }

     */
}
