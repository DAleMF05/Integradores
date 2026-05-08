package com.example.integrador3.dto;

import lombok.*;


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
