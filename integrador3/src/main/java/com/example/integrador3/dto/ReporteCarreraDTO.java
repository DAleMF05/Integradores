package com.example.integrador3.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ReporteCarreraDTO {
    private String nombreCarrera;
    private int anio;
    private long inscriptos;
    private long egresados;

    public ReporteCarreraDTO(String nombreCarrera, Integer anio, long inscriptos, long egresados) {
        this.nombreCarrera = nombreCarrera;
        this.anio = (anio != null) ? anio : 0;
        this.inscriptos = inscriptos;
        this.egresados = egresados;
    }
}
