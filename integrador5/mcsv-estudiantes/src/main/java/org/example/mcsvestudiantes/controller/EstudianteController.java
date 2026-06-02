package org.example.mcsvestudiantes.controller;

import org.example.mcsvestudiantes.dto.EstudianteDTO;
import org.example.mcsvestudiantes.service.EstudianteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @PostMapping("")
    public ResponseEntity<EstudianteDTO> save(@RequestBody EstudianteDTO estudiante) {
        final var result = this.estudianteService.save(estudiante);
        return ResponseEntity.accepted().body(result);
    }

    //ordenados por dni
    @GetMapping("")
    public List<EstudianteDTO> getAll(){
        return this.estudianteService.getAll();
    }

    @GetMapping("/{id}")
    public EstudianteDTO getById( @PathVariable Long id ) {
        return this.estudianteService.getById(id);
    }

    @GetMapping("/dni/{dni}")
    public EstudianteDTO findByDni( @PathVariable String dni) {
        return this.estudianteService.findByDni(dni);
    }

    @GetMapping("/lu/{lu}")
    public EstudianteDTO getByLibretaUni( @PathVariable String lu ) {
        return this.estudianteService.getByLibretaUni((lu));
    }

    @GetMapping("/genero/{gen}")
    public List<EstudianteDTO> getByGenero(@PathVariable char gen){
        return this.estudianteService.getByGenero(gen);
    }

    @GetMapping("/getByDniYCiudad")
    public List<EstudianteDTO> getByCarreraYciudad(@RequestParam String dni, @RequestParam String ciudad) {
        return this.estudianteService.getByDniAndCiudad(dni, ciudad);
    }

}
