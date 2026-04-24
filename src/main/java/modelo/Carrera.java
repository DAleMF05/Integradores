package modelo;

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
    private Integer idCarrera;
    private String nombre;

    @OneToMany(mappedBy = "carrera")
    private List<Inscripcion> inscripciones = new ArrayList<>();

    // getters y setters
}
