package com.example.integrador3.entities;


import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "inscripcion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInscripcion;
    private Integer fechaInsc;
    private Integer fechaGrad;
    private int antiguedad;

    @ManyToOne
    @JoinColumn(name = "idEstudiante")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "idCarrera")
    private Carrera carrera;

    public Inscripcion(int fechaInsc, int fechaGrad, int antiguedad, Estudiante estudiante, Carrera carrera) {
        this.fechaInsc = fechaInsc;
        this.fechaGrad = fechaGrad;
        this.antiguedad = antiguedad;
        this.estudiante = estudiante;
        this.carrera = carrera;
    }


}
