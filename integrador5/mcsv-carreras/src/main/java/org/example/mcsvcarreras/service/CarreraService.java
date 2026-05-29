package org.example.mcsvcarreras.service;

import lombok.RequiredArgsConstructor;
import org.example.mcsvcarreras.clients.IEstudianteClientRest;
import org.example.mcsvcarreras.dto.CarreraDTO;
import org.example.mcsvcarreras.dto.CarreraInsDTO;
import org.example.mcsvcarreras.dto.ReporteCarreraDTO;
import org.example.mcsvcarreras.entities.Carrera;
import org.example.mcsvcarreras.repository.ICarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarreraService implements ICarreraService {

    @Autowired
    private ICarreraRepository carreraRepository;

    @Autowired
    private IEstudianteClientRest iEstudianteClientRest;

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
    public CarreraDTO findByNombre(String nombre) {
        return this.carreraRepository.findByNombre(nombre).map(CarreraDTO::new).orElseThrow(() -> new RuntimeException("Carrera no encontrada"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarreraInsDTO> buscarInscriptos() {
        return this.carreraRepository.buscarInscriptos();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReporteCarreraDTO> generarReporte() {
        return this.carreraRepository.generarReporte();
    }


//    @Override
//    @Transactional(readOnly = true)
//    public List<CarreraInsDTO> buscarPorInscriptos() {
//        return this.carreraRepository.buscarPorInscriptos();
//    }




}