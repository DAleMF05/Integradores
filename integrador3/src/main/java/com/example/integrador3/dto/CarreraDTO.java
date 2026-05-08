package com.example.integrador3.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class CarreraDTO {

    private Long idCarrera;
    private String nombre;
    private int duracion;
    private List<InscripcionDTO> inscripciones;
}