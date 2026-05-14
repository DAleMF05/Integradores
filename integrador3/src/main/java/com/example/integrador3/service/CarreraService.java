package com.example.integrador3.service;

import com.example.integrador3.dto.CarreraDTO;
import com.example.integrador3.dto.CarreraInsDTO;
import com.example.integrador3.entities.Carrera;
import com.example.integrador3.repository.ICarreraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarreraService implements ICarreraService {

    @Autowired
    private ICarreraRepository carreraRepository;

    @Override
    @Transactional
    public CarreraDTO save(CarreraDTO carrera) {
        final var carre = new Carrera(carrera);
        final var result = this.carreraRepository.save(carre);
        return new CarreraDTO(result);
    }


    @Override
    @Transactional(readOnly = true)
    public List<CarreraDTO> getAll() {
        return this.carreraRepository.findAll().stream().map(CarreraDTO::new).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CarreraDTO getById(Long id) {
        return this.carreraRepository.findById(id).map(CarreraDTO::new).orElseThrow(() -> new RuntimeException("Carrera no encontrada"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarreraInsDTO> buscarInscriptos() {
        return this.carreraRepository.buscarInscriptos();
    }


//    @Override
//    @Transactional(readOnly = true)
//    public List<CarreraInsDTO> buscarPorInscriptos() {
//        return this.carreraRepository.buscarPorInscriptos();
//    }




}
