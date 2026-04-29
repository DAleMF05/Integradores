package dto;

import lombok.*;

import java.time.LocalDate;

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


}
