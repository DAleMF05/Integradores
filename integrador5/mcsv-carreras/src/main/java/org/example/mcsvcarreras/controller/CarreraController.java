package org.example.mcsvcarreras.controller;

import org.example.mcsvcarreras.dto.CarreraDTO;
import org.example.mcsvcarreras.dto.CarreraInsDTO;
import org.example.mcsvcarreras.dto.ReporteCarreraDTO;
import org.example.mcsvcarreras.service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/inscriptos")
    public List<CarreraInsDTO> buscarIncriptos(){
        return this.carreraService.buscarInscriptos();
    }

    @GetMapping("/reporte")
    public List<ReporteCarreraDTO> generarReporte() {
        return this.carreraService.generarReporte();
    }


}