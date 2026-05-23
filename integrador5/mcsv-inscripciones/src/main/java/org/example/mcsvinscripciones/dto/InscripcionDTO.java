package org.example.mcsvinscripciones.dto;

import lombok.*;
import org.example.mcsvinscripciones.entities.Inscripcion;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InscripcionDTO {

    private String dniEstudiante;
    private String nombreCarrera;
    private int fechaInsc;
    private int fechaGrad;
    private int antiguedad;

    public InscripcionDTO(Inscripcion i) {
        this.dniEstudiante = i.getEstudiante().getDni();
        this.nombreCarrera = i.getCarrera().getNombre();
        this.fechaInsc = i.getFechaInsc();
        this.fechaGrad = i.getFechaGrad();
        this.antiguedad = i.getAntiguedad();
    }

}
