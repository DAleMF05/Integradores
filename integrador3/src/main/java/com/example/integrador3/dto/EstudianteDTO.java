package com.example.integrador3.dto;

import com.example.integrador3.entities.Estudiante;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class EstudianteDTO {

    private Long idEstudiante;
    private String dni;
    private String nombre;
    private String apellido;
    private int edad;
    private char genero;
    private String ciudad;
    private String numLibretaUni;


    public EstudianteDTO(Estudiante e) {
        this.idEstudiante = e.getIdEstudiante();
        this.dni = e.getDni();
        this.nombre = e.getNombre();
        this.apellido = e.getApellido();
        this.edad = e.getEdad();
        this.genero = e.getGenero();
        this.ciudad = e.getCiudad();
        this.numLibretaUni = e.getNumLibretaUni();
    }


}
