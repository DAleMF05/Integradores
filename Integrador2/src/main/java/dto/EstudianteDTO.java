package dto;

import lombok.*;
import modelo.Estudiante;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class EstudianteDTO {

    private String idEstudiante;
    private String nombre;
    private String apellido;
    private int edad;
    private char genero;
    private String ciudad;
    private String numLibretaUni;

}
