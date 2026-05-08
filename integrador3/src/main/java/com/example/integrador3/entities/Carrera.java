package com.example.integrador3.entities;

import com.example.integrador3.dto.CarreraDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carrera")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarrera;
    private String nombre;
    private int duracion;

    @OneToMany(mappedBy = "carrera")
    private List<Inscripcion> inscripciones;

    // getters y setters

    public Carrera(String nombre, int duracion) {
        this.duracion = duracion;
        this.nombre = nombre;
        this.inscripciones = new ArrayList<>();
    }

    public Carrera(CarreraDTO carre) {

    }
}