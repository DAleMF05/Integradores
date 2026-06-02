package org.example.mcsvcarreras.entities;

import lombok.*;
import org.example.mcsvcarreras.dto.CarreraDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "carreras")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Carrera {

    @Id
    private String idCarrera;

    private String nombre;
    private int duracion;

    public Carrera(String nombre, int duracion) {
        this.nombre = nombre;
        this.duracion = duracion;
    }

    public Carrera(CarreraDTO carre) {
        this.idCarrera = carre.getIdCarrera();
        this.nombre = carre.getNombre();
        this.duracion = carre.getDuracion();
    }
}