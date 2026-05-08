package com.example.integrador3.controller;

import com.example.integrador3.dto.CarreraDTO;
import com.example.integrador3.entities.Carrera;
import com.example.integrador3.repository.ICarreraRepository;
import com.example.integrador3.service.CarreraService;
import com.example.integrador3.service.ICarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    @PostMapping("")
    public ResponseEntity<CarreraDTO> saveCarrera(@RequestBody CarreraDTO carrera) {
        final var result = this.carreraService.saveCarrera(carrera);
        return ResponseEntity.accepted().body(result);

    }
}
