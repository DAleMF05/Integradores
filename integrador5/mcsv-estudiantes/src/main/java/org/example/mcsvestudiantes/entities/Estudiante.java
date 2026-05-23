package org.example.mcsvestudiantes.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.mcsvestudiantes.dto.EstudianteDTO;

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
    private Long idEstudiante;
    @Column(unique = true, nullable = false)
    private String dni;
    private String nombre;
    private String apellido;
    private int edad;
    private char genero;
    private String ciudad;
    @Column(unique = true, nullable = false)
    private String numLibretaUni;

//    @OneToMany(mappedBy = "estudiante")
//    private List<Inscripcion> inscripciones;

    public Estudiante(String dni, String nombre, String apellido, int edad, char genero, String ciudad, String numLibretaUni) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudad = ciudad;
        this.numLibretaUni = numLibretaUni;
//        this.inscripciones = new ArrayList<>();
    }

    public Estudiante (EstudianteDTO estDTO) {
        this.dni = estDTO.getDni();
        this.nombre = estDTO.getNombre();
        this.apellido = estDTO.getApellido();
        this.edad = estDTO.getEdad();
        this.genero = estDTO.getGenero();
        this.ciudad = estDTO.getCiudad();
        this.numLibretaUni = estDTO.getNumLibretaUni();
        this.inscripciones = new ArrayList<>();
    }

    // getters y setters
}
