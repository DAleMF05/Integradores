package org.example.mcsvestudiantes.repository;

import org.example.mcsvestudiantes.dto.EstudianteDTO;
import org.example.mcsvestudiantes.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEstudianteRepository  extends JpaRepository<Estudiante, Long> {

    Optional<Estudiante> findByDni(String dni);

    Optional<Estudiante> findByNumLibretaUni(String numLibretaUni);

    @Query("""
        SELECT new org.example.mcsvestudiantes.dto.EstudianteDTO(
            e.dni,
            e.nombre,
            e.apellido,
            e.edad,
            e.genero,
            e.ciudad,
            e.numLibretaUni)
        FROM Estudiante e
        ORDER BY e.dni
    """)
    List<EstudianteDTO> getAll();

    @Query("""
        SELECT new org.example.mcsvestudiantes.dto.EstudianteDTO(
            e.dni,
            e.nombre,
            e.apellido,
            e.edad,
            e.genero,
            e.ciudad,
            e.numLibretaUni)
            FROM Estudiante e
            WHERE e.genero = :gen
    """)
    List<EstudianteDTO> getByGenero(char gen);

    @Query("""
        SELECT new org.example.mcsvestudiantes.dto.EstudianteDTO(
            e.dni,
            e.nombre,
            e.apellido,
            e.edad,
            e.genero,
            e.ciudad,
            e.numLibretaUni)
        FROM Estudiante e
        WHERE e.dni = :dni AND e.ciudad = :ciudad
        """)
    List<EstudianteDTO> getEstudianteByDniAndCiudad(String dni, String ciudad);
}
