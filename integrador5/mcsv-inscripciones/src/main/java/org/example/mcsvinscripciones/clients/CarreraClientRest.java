package org.example.mcsvinscripciones.clients;


import org.example.mcsvinscripciones.models.CarreraM;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "mcsv-carrera", url = "http://localhost:8002")
public interface CarreraClientRest {

    @GetMapping("/api/carrera/{id}")
    CarreraM getById (@PathVariable Long id);

}
