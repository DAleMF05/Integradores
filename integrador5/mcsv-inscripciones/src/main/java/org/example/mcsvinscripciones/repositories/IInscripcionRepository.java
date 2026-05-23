package org.example.mcsvinscripciones.repositories;

import org.example.mcsvinscripciones.dto.InscripcionDTO;
import org.example.mcsvinscripciones.entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface IInscripcionRepository extends JpaRepository<Inscripcion, Long> {
//    @Query("""
//        SELECT new org.example.mcsvinscripciones.dto.InscripcionDTO(
//            e.dni,
//            c.nombre,
//            i.fechaInsc,
//            i.fechaGrad,
//            i.antiguedad)
//        FROM Inscripcion i JOIN i.estudiante e JOIN i.carrera c
//        """)
    List<InscripcionDTO> getAll();
}
