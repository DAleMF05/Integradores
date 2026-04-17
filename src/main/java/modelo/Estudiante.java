package modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estudiante")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEstudiante;
    private String nombre;
    private String apellido;
    private int edad;
    private char genero;
    private String dni;
    private String ciudad;
    private String numLibretaUni;

    @OneToMany(mappedBy = "estudiante")
    private List<Inscripcion> inscripciones = new ArrayList<>();

    public Estudiante(String nombre, String apellido, int edad, char genero, String dni, String ciudad, String numLibretaUni) {
    }

    // getters y setters
}
