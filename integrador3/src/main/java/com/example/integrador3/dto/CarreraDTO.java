package com.example.integrador3.dto;

import com.example.integrador3.entities.Carrera;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@JsonPropertyOrder({
        "idCarrera",
        "nombre",
        "duracion",
        "inscripciones"
})

public class CarreraDTO {

    private Long idCarrera;
    private String nombre;
    private int duracion;


    public CarreraDTO(Carrera carrera) {
        this.idCarrera = carrera.getIdCarrera();
        this.nombre = carrera.getNombre();
        this.duracion = carrera.getDuracion();
    }

}