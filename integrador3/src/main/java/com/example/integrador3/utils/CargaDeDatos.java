package com.example.integrador3.utils;


import com.example.integrador3.repository.ICarreraRepository;
import com.example.integrador3.repository.IEstudianteRepository;
import com.example.integrador3.repository.IInscripcionRepository;
import com.example.integrador3.entities.*;
import com.opencsv.CSVReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Component
public class CargaDeDatos {

    private final ICarreraRepository carreraRepository;
    private final IEstudianteRepository estudianteRepository;
    private final IInscripcionRepository inscripcionRepository;


    @Autowired
    public CargaDeDatos(ICarreraRepository carreraRepository, IEstudianteRepository estudianteRepository, IInscripcionRepository inscripcionRepository) {
        this.carreraRepository = carreraRepository;
        this.estudianteRepository = estudianteRepository;
        this.inscripcionRepository = inscripcionRepository;
    }


    public void cargarDatosDesdeCSV() throws IOException {
        File carreraCSV = ResourceUtils.getFile("src/main/resources/carreras.csv");
        File estudianteCSV = ResourceUtils.getFile("src/main/resources/estudiantes.csv");
        File inscripcionCSV = ResourceUtils.getFile("src/main/resources/estudianteCarrera.csv");

        try (CSVReader reader = new CSVReader(new FileReader(carreraCSV))) {
            String[] linea;
            reader.readNext(); // salta cabecera


            while ((linea = reader.readNext()) != null) {
                Carrera carrera = new Carrera();
                carrera.setNombre(linea[1]);
                carrera.setDuracion(Integer.parseInt(linea[2]));
                carreraRepository .save(carrera);// Guarda el cliente en la base de datos
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (CSVReader reader = new CSVReader(new FileReader(estudianteCSV))) {
            String[] linea;
            reader.readNext(); // salta cabecera

            while ((linea = reader.readNext()) != null) {
                Estudiante estudiante = new Estudiante();
                estudiante.setDni(linea[0]);
                estudiante.setNombre(linea[1]);
                estudiante.setApellido(linea[2]);
                estudiante.setEdad(Integer.parseInt(linea[3]));
                estudiante.setGenero(linea[4].charAt(0));
                estudiante.setCiudad(linea[5]);
                estudiante.setNumLibretaUni(linea[6]);
                estudianteRepository.save(estudiante);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        try (CSVReader reader = new CSVReader(new FileReader(inscripcionCSV))) {
            String[] linea;
            reader.readNext(); // salta cabecera


            while ((linea = reader.readNext()) != null) {

                int dni = Integer.parseInt(linea[1]);
                int idCarrera = Integer.parseInt(linea[2]);

                Estudiante estudiante = estudianteRepository
                        .findById(Long.valueOf(dni))
                        .orElse(null);

                Carrera carrera = carreraRepository
                        .findById(Long.valueOf(idCarrera))
                        .orElse(null);

                if (estudiante != null && carrera != null) {

                    Inscripcion inscripcion = new Inscripcion();

                    inscripcion.setEstudiante(estudiante);
                    inscripcion.setCarrera(carrera);

                    inscripcion.setFechaInsc(Integer.parseInt(linea[3]));
                    inscripcion.setFechaGrad(Integer.parseInt(linea[4]));
                    inscripcion.setAntiguedad(Integer.parseInt(linea[5]));

                    inscripcionRepository.save(inscripcion);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}










