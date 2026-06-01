package org.example.mcsvinscripciones.utils;

import com.opencsv.CSVReader;
import org.example.mcsvinscripciones.entities.Inscripcion;
import org.example.mcsvinscripciones.repositories.IInscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;

@Component
public class CargaDeInscripciones {
    private final IInscripcionRepository inscripcionRepository;

    @Autowired
    public CargaDeInscripciones(IInscripcionRepository inscripcionRepository) {
        this.inscripcionRepository = inscripcionRepository;
    }

    public void cargarDatosDesdeCSV() {
        try {
            File inscripcionCSV = ResourceUtils.getFile("classpath:inscripciones.csv");

            try (CSVReader reader = new CSVReader(new FileReader(inscripcionCSV))) {
                String[] linea;
                reader.readNext(); // Salta cabecera

                while ((linea = reader.readNext()) != null) {
                    Inscripcion inscripcion = new Inscripcion();

                    // Asignamos directamente los IDs lógicos que vienen en el CSV
                    inscripcion.setIdEstudiante(linea[1]); // idEstudiante
                    inscripcion.setIdCarrera(Long.parseLong(linea[2])); // idCarrera

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
