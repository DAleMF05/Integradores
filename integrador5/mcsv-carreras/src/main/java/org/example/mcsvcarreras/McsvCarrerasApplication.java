package org.example.mcsvcarreras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients // anotacion para habilitar la comunicacion entre microservicios con un cliente Feign
@SpringBootApplication
public class McsvCarrerasApplication {

    public static void main(String[] args) {
        SpringApplication.run(McsvCarrerasApplication.class, args);
    }

}
