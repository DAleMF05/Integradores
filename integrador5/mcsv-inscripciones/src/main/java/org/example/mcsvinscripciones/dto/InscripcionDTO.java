package org.example.mcsvinscripciones.dto;

import lombok.*;
import org.example.mcsvinscripciones.entities.Inscripcion;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class InscripcionDTO {

    private Long idInscripcion;
    private int fechaInsc;
    private int fechaGrad;
    private int antiguedad;
    private String dniEstudiante;
    private String idCarrera;

    public InscripcionDTO(Inscripcion inscripcion) {
//        this.idInscripcion = inscripcion.getIdInscripcion();
        this.fechaInsc = inscripcion.getFechaInsc();
        this.fechaGrad = inscripcion.getFechaGrad();
        this.antiguedad = inscripcion.getAntiguedad();
        this.dniEstudiante = inscripcion.getDniEstudiante();
        this.idCarrera = inscripcion.getIdCarrera();
    }
}
