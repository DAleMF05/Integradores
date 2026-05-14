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
    private List<Inscripcion> inscripciones = new ArrayList<>();


    // getters y setters

    public Carrera(String nombre, int duracion) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.inscripciones = new ArrayList<>();
    }

    public Carrera(CarreraDTO carre) {
        this.nombre = carre.getNombre();
        this.duracion = carre.getDuracion();
        this.inscripciones = new ArrayList<>();

    }
}