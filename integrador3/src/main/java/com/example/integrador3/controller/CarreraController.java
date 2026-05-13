package com.example.integrador3.controller;

import com.example.integrador3.dto.CarreraDTO;
import com.example.integrador3.dto.CarreraInsDTO;
import com.example.integrador3.entities.Carrera;
import com.example.integrador3.repository.ICarreraRepository;
import com.example.integrador3.service.CarreraService;
import com.example.integrador3.service.ICarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    @PostMapping("")
    public ResponseEntity<CarreraDTO> saveCarrera(@RequestBody CarreraDTO carrera) {
        final var result = this.carreraService.save(carrera);
        return ResponseEntity.accepted().body(result);

    }

    @GetMapping("")
    public List<CarreraDTO> getAll(){
        return this.carreraService.getAll();
    }

    @GetMapping("/{id}")
    public CarreraDTO getById(@PathVariable Long id){
        return this.carreraService.getById(id);
    }



}
