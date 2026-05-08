package com.example.integrador3.repository;

import com.example.integrador3.entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInscripcionRepository extends JpaRepository<Inscripcion, Long> {
}
