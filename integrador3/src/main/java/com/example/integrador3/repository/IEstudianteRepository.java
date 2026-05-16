package com.example.integrador3.repository;

import com.example.integrador3.dto.EstudianteDTO;
import com.example.integrador3.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEstudianteRepository extends JpaRepository<Estudiante, Long> {

    Optional<Estudiante> findByDni(String dni);

    Optional<Estudiante> findByNumLibretaUni(String numLibretaUni);

}
