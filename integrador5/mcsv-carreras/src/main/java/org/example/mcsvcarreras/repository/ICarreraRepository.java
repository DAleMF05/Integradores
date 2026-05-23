package org.example.mcsvcarreras.repository;

import org.example.mcsvcarreras.dto.CarreraInsDTO;
import org.example.mcsvcarreras.dto.ReporteCarreraDTO;
import org.example.mcsvcarreras.entities.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICarreraRepository extends JpaRepository<Carrera, Long> {

    Optional<Carrera> findByNombre(String nombre);

    @Query("""
        SELECT new org.example.mcsvcarreras.dto.CarreraInsDTO(
            c.nombre,
            c.duracion,
            COUNT(i.idInscripcion)
        )
        FROM Carrera c
        JOIN c.inscripciones i
        GROUP BY c.nombre, c.duracion
        ORDER BY COUNT(i.idInscripcion) DESC
    """)
    List<CarreraInsDTO> buscarInscriptos();

    @Query("""
        SELECT new org.example.mcsvcarreras.dto.ReporteCarreraDTO(
            c.nombre,
            i.fechaInsc,
            COUNT(i.idInscripcion),
            SUM(CASE WHEN i.fechaGrad IS NOT NULL AND i.fechaGrad != 0 THEN 1 ELSE 0 END)
        )
        FROM Carrera c
        JOIN c.inscripciones i
        GROUP BY c.nombre, i.fechaInsc
        ORDER BY c.nombre ASC, i.fechaInsc ASC
    """)
    List<ReporteCarreraDTO> generarReporte();





}
