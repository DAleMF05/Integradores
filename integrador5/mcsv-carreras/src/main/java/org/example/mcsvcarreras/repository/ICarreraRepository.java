package org.example.mcsvcarreras.repository;

import org.example.mcsvcarreras.entities.Carrera;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ICarreraRepository extends MongoRepository<Carrera, String> {

    Optional<Carrera> findByNombre(String nombre);

}