package org.example.mcsvcarreras.dto;

import lombok.*;
import org.example.mcsvcarreras.entities.Carrera;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class CarreraDTO {

    private String idCarrera;
    private String nombre;
    private int duracion;


    public CarreraDTO(Carrera carrera) {
        this.idCarrera = carrera.getIdCarrera();
        this.nombre = carrera.getNombre();
        this.duracion = carrera.getDuracion();
    }

}
