package com.example.integrador3.dto;

import com.example.integrador3.entities.Carrera;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarreraInsDTO {
    private Long idCarrera;
    private String nombre;
    private int duracion;
    private long inscriptos;

    public CarreraInsDTO(Long idCarrera, String nombre, int duracion, Long cantidadInscriptos) {

        this.idCarrera = idCarrera;
        this.nombre = nombre;
        this.duracion = duracion;
        this.inscriptos = cantidadInscriptos;
    }
}
