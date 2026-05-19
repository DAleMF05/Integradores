package com.example.integrador3.dto;

import com.example.integrador3.entities.Carrera;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarreraInsDTO {
    private String nombre;
    private int duracion;
    private long inscriptos;

    public CarreraInsDTO(String nombre, int duracion, Long cantidadInscriptos) {

        this.nombre = nombre;
        this.duracion = duracion;
        this.inscriptos = cantidadInscriptos;
    }

    public CarreraInsDTO(Carrera carrera) {
        this.nombre = carrera.getNombre();
        this.duracion = carrera.getDuracion();
        this.inscriptos = 0;

    }
}
