package com.example.integrador3.repository;

import com.example.integrador3.dto.CarreraDTO;
import com.example.integrador3.dto.CarreraInsDTO;
import com.example.integrador3.entities.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface ICarreraRepository extends JpaRepository<Carrera, Long> {

    @Query("""
        SELECT new com.example.integrador3.dto.CarreraInsDTO(
            c.idCarrera,
            c.nombre,
            c.duracion,
            COUNT(i.idInscripcion)
        )
        FROM Carrera c
        JOIN c.inscripciones i
        GROUP BY c.idCarrera, c.nombre, c.duracion
        ORDER BY COUNT(i.idInscripcion) DESC
    """)
    List<CarreraInsDTO> buscarInscriptos();





}
