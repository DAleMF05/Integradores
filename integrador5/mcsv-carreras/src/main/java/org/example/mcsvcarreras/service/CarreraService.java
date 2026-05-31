package org.example.mcsvcarreras.service;

import lombok.RequiredArgsConstructor;
import org.example.mcsvcarreras.clients.IEstudianteClientRest;
import org.example.mcsvcarreras.dto.CarreraDTO;
import org.example.mcsvcarreras.entities.Carrera;
import org.example.mcsvcarreras.repository.ICarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarreraService implements ICarreraService {

    @Autowired
    private ICarreraRepository carreraRepository;

    @Autowired
    private IEstudianteClientRest iEstudianteClientRest;

    @Override
    public CarreraDTO save(CarreraDTO carrera) {
        final var carre = new Carrera(carrera);
        final var result = this.carreraRepository.save(carre);
        return new CarreraDTO(result);
    }

    @Override
    public List<CarreraDTO> getAll() {
        return this.carreraRepository.findAll()
                .stream()
                .map(CarreraDTO::new)
                .toList();
    }

    @Override
    public CarreraDTO getById(String id) {
        return this.carreraRepository.findById(id)
                .map(CarreraDTO::new)
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));
    }

    @Override
    public CarreraDTO findByNombre(String nombre) {
        return this.carreraRepository.findByNombre(nombre)
                .map(CarreraDTO::new)
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));
    }

}