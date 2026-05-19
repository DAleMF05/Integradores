package com.example.integrador3.dto;

import com.example.integrador3.entities.Carrera;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@JsonPropertyOrder({
        "nombre",
        "duracion",
        "inscripciones"
})

public class CarreraDTO {

    private String nombre;
    private int duracion;


    public CarreraDTO(Carrera carrera) {
        this.nombre = carrera.getNombre();
        this.duracion = carrera.getDuracion();
    }

}