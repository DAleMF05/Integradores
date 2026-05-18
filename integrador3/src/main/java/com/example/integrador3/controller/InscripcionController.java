package com.example.integrador3.controller;

import com.example.integrador3.dto.InscripcionDTO;
import com.example.integrador3.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @GetMapping("")
    public List<InscripcionDTO> getAll() {
        return this.inscripcionService.getAll();
    }

    @PostMapping("/matricular")
    public ResponseEntity<InscripcionDTO> matricularEstudiante(@RequestBody InscripcionDTO inscripcionDTO) {
        InscripcionDTO newInscripcion = inscripcionService.save(inscripcionDTO);
        return ResponseEntity.accepted().body(newInscripcion);
    }

}
