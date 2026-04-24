package modelo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "inscripcion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInscripcion;
    private LocalDate fechaInsc;
    private LocalDate fechaGrad;

    @ManyToOne
    @JoinColumn(name = "idEstudiante")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "idCarrera")
    private Carrera carrera;

    // getters y setters
}