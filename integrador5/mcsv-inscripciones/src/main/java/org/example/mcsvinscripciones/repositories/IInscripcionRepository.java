package org.example.mcsvinscripciones.repositories;

import org.example.mcsvinscripciones.dto.InscripcionDTO;
import org.example.mcsvinscripciones.entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface IInscripcionRepository extends JpaRepository<Inscripcion, Long> {


}
