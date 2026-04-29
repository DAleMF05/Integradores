package dto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarreraInsDTO {
    private int idCarrera;
    private String nombre;
    private int duracion;
    private long Inscriptos;
}
