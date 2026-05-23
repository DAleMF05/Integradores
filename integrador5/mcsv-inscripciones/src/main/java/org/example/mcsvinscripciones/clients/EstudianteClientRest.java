package org.example.mcsvinscripciones.clients;

import org.example.mcsvinscripciones.models.EstudianteM;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "mcsv-estudiantes", url = "http://localhost:8001")
public interface EstudianteClientRest {

    @GetMapping("/api/estudiante/{id}")
    EstudianteM getDni (@PathVariable Long id);
}
