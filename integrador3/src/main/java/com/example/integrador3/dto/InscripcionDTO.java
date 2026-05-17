package com.example.integrador3.dto;

import com.example.integrador3.entities.Inscripcion;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InscripcionDTO {

 private Long idInscripcion;
    private Long idEstudiante;
    private Long idCarrera;
    private int fechaInsc;
    private int fechaGrad;
    private int antiguedad;

    public InscripcionDTO(Inscripcion i) {
        this.idInscripcion = i.getIdInscripcion();
        this.idEstudiante = i.getEstudiante().getIdEstudiante();
        this.idCarrera = i.getCarrera().getIdCarrera();
        this.fechaInsc = i.getFechaInsc();
        this.fechaGrad = i.getFechaGrad();
        this.antiguedad = i.getAntiguedad();
    }

    
}
