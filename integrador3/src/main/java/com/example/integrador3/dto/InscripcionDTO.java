package com.example.integrador3.dto;

import com.example.integrador3.entities.Inscripcion;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InscripcionDTO {

    private int idInscripcion;
    private String idEstudiante; //poner nombre!
    private int idCarrera;
    private int fechaInsc;
    private int fechaGrad;
    private int antiguedad;


    public InscripcionDTO(Long idInscripcion, String idEstudiante, Long idCarrera, int fechaInsc, int fechaGrad, int antiguedad) {
        this.idInscripcion = idInscripcion.intValue();
        this.idEstudiante = idEstudiante;
        this.idCarrera = idCarrera.intValue();
        this.fechaInsc = fechaInsc;
        this.fechaGrad = fechaGrad;
        this.antiguedad = antiguedad;
    }
}
