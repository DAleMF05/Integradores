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
    private Long idEstudiante; //poner nombre!
    private Long idCarrera;
    private int fechaInsc;
    private int fechaGrad;
    private int antiguedad;


    public InscripcionDTO(Long idInscripcion, Long idEstudiante, Long idCarrera, int fechaInsc, int fechaGrad, int antiguedad) {
        this.idInscripcion = idInscripcion.intValue();
        this.idEstudiante = idEstudiante.longValue();
        this.idCarrera = idCarrera.longValue();
        this.fechaInsc = fechaInsc;
        this.fechaGrad = fechaGrad;
        this.antiguedad = antiguedad;
    }
}
