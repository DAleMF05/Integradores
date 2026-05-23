package org.example.mcsvcarreras.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "mcsv-inscripciones", url = "http://localhost:8003")
public interface IInscripcionClientRest {

//    @GetMapping("/api/carrera/{id}")
//    CarreraM getNombre (@PathVariable Long id);

}
