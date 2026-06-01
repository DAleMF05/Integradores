package org.example.mcsvinscripciones;

import org.example.mcsvinscripciones.utils.CargaDeInscripciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class McsvInscripcionesApplication implements CommandLineRunner {

    @Autowired
    private CargaDeInscripciones cargaDeDatos; // 2. Inyectas tu cargador de toda la vida

    public static void main(String[] args) {
        SpringApplication.run(McsvInscripcionesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //Este metodo se dispara solo y sabe usar la carga de inscripciones
        cargaDeDatos.cargarDatosDesdeCSV();
    }

}
