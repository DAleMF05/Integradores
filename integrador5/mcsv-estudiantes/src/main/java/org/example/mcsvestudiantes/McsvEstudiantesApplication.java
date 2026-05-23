package org.example.mcsvestudiantes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients // anotacion para habilitar la comunicacion entre microservicios con un cliente Feign
public class McsvEstudiantesApplication {

    public static void main(String[] args) {
        SpringApplication.run(McsvEstudiantesApplication.class, args);
    }

}
