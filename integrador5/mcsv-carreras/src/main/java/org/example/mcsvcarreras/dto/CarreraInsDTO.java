package org.example.mcsvcarreras.dto;

import lombok.*;
import org.example.mcsvcarreras.entities.Carrera;

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