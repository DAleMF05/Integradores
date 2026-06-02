package org.example.mcsvinscripciones.clients;

import org.example.mcsvinscripciones.models.EstudianteM;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "mcsv-estudiantes", url = "http://localhost:8001")
public interface EstudianteClientRest {

    @GetMapping("/api/estudiantes/{dni}")
    EstudianteM getByDni(@PathVariable String dni);

    @GetMapping("/api/estudiantes/getByDniYCiudad")
    List<EstudianteM> getByDniYCiudad(@RequestParam("dni") List<String> dni, @RequestParam("ciudad") String ciudad);
}
