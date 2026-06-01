package org.example.mcsvinscripciones.dto;

import lombok.*;
import org.example.mcsvinscripciones.entities.Inscripcion;
import org.example.mcsvinscripciones.models.CarreraM;
import org.example.mcsvinscripciones.models.EstudianteM;

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
    private String idEstudiante;
    private Long idCarrera;
    private EstudianteM estudiante;
    private CarreraM carrera;

    public InscripcionDTO(Inscripcion inscripcion) {
        this.idInscripcion = inscripcion.getIdInscripcion();
        this.fechaInsc = inscripcion.getFechaInsc();
        this.fechaGrad = inscripcion.getFechaGrad();
        this.antiguedad = inscripcion.getAntiguedad();
        this.idEstudiante = inscripcion.getIdEstudiante();
        this.idCarrera = inscripcion.getIdCarrera();
        // Los objetos estudiante y carrera se setean desde el Service usando Feign
        this.estudiante = inscripcion.getEstudiante();
        this.carrera = inscripcion.getCarrera();
    }
}
