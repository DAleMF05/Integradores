package modelo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "inscripcion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInscripcion;
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

    // getters y setters
}