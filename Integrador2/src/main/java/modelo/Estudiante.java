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
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEstudiante;
    @Column(unique = true, nullable = false)
    private String dni;
    private String nombre;
    private String apellido;
    private int edad;
    private char genero;
    private String ciudad;
    @Column(unique = true, nullable = false)
    private String numLibretaUni;

    @OneToMany(mappedBy = "estudiante")
    private List<Inscripcion> inscripciones;

    public Estudiante(String dni, String nombre, String apellido, int edad, char genero, String ciudad, String numLibretaUni) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudad = ciudad;
        this.numLibretaUni = numLibretaUni;
        this.inscripciones = new ArrayList<>();
    }

    // getters y setters
}
