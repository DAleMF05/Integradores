package org.example.mcsvinscripciones.controller;

import org.example.mcsvinscripciones.dto.InscripcionDTO;
import org.example.mcsvinscripciones.models.EstudianteM;
import org.example.mcsvinscripciones.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inscripciones")
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

        @GetMapping("/estudiantesPorCarrera/{nombreCarrera}/{ciudad}")
        public ResponseEntity<List<EstudianteM>> getEstudiantesPorCarreraYCiudad(@PathVariable String nombreCarrera, @PathVariable String ciudad) {

            List<EstudianteM> estudiantes = inscripcionService.getByCarreraYCiudad(nombreCarrera, ciudad);

            return ResponseEntity.ok(estudiantes);
        }
}
