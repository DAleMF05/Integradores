package org.example.mcsvcarreras.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//se usa para que el microservicio mcsv-carreras pueda llamar por HTTP al microservicio de estudiantes.
@FeignClient(name = "mcsv-estudiantes", url = "http://localhost:8001")
public interface IEstudianteClientRest {


}
