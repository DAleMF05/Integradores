package com.example.integrador3.repository;

import com.example.integrador3.dto.InscripcionDTO;
import com.example.integrador3.entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInscripcionRepository extends JpaRepository<Inscripcion, Long> {

    @Query("""
        SELECT new com.example.integrador3.dto.InscripcionDTO(
            e.dni,
            c.nombre,
            i.fechaInsc,
            i.fechaGrad,
            i.antiguedad)
        FROM Inscripcion i JOIN i.estudiante e JOIN i.carrera c
        """)
    List<InscripcionDTO> getAll();
}
