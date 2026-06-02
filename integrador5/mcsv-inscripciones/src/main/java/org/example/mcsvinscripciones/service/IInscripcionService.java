package org.example.mcsvinscripciones.service;

import org.example.mcsvinscripciones.dto.InscripcionDTO;
import org.example.mcsvinscripciones.models.EstudianteM;

import java.util.List;
import java.util.Optional;


public interface IInscripcionService {

    InscripcionDTO save(InscripcionDTO inscripcionDTO);

    List<EstudianteM> getByCarreraYCiudad(String nombreCarrera, String ciudad);
}
