package dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InscripcionDTO {

    private int idEstudiante; //poner nombre!
    private int idCarrera;
    private LocalDate fechaInsc;
    private LocalDate fechaGrad;
}
