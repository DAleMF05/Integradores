package dto;

import lombok.*;
import modelo.Estudiante;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarreraReporteDTO {
    private String nombre;
    private Integer anio;
    private Integer inscriptos;
    private Integer graduados;



    // hardcodeando constructores ya que hibernate lee el count() como Long
    public CarreraReporteDTO(String nombre, Integer anio, Long inscriptos, Integer graduados) {
        this.nombre = nombre;
        this.anio = anio;
        this.inscriptos = inscriptos.intValue();
        this.graduados = graduados;
    }

    public CarreraReporteDTO(String nombre, Integer anio, Integer inscriptos, Long graduados) {
       this.nombre = nombre;
       this.anio = anio;
       this.inscriptos = inscriptos;
       this.graduados = graduados.intValue();
    }

}
