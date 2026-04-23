package dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class EstudianteDTO {

    private int idEstudiante;
    private String nombre;
    private String apellido;
    private int edad;
    private char genero;
    private String dni;
    private String ciudad;
    private String numLibretaUni;

}
