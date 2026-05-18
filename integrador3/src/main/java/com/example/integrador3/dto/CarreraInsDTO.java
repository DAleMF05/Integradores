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

    public CarreraInsDTO(Carrera carrera) {
        this.idCarrera = carrera.getIdCarrera();
        this.nombre = carrera.getNombre();
        this.duracion = carrera.getDuracion();
        this.inscriptos = 0;

    }
}
