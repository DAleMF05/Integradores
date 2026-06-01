package org.example.mcsvinscripciones.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.mcsvinscripciones.models.CarreraM;
import org.example.mcsvinscripciones.models.EstudianteM;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "inscripciones")
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInscripcion;
    private String idEstudiante;
    private Long idCarrera;
    private Integer fechaInsc;
    private Integer fechaGrad;
    private int antiguedad;

    @Transient
    private EstudianteM estudiante;
    @Transient
    private CarreraM carrera;

    public Inscripcion(Integer fechaInsc, Integer fechaGrad, int antiguedad, Long idCarrera, String idEstudiante) {
        this.fechaInsc = fechaInsc;
        this.fechaGrad = fechaGrad;
        this.antiguedad = antiguedad;
        this.idCarrera = idCarrera;
        this.idEstudiante = idEstudiante;
    }
}
