package org.example.mcsvestudiantes.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.cloud.openfeign.EnableFeignClients;


@FeignClient(name = "mscv-carreras", url = "http://localhost:8002")
public interface CarreraClientRest {

}
