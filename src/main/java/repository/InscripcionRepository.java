package repository;

import com.opencsv.CSVReader;
import factory.JPAUtil;
import jakarta.persistence.EntityManager;
import modelo.Inscripcion;

import java.io.FileReader;
import java.time.LocalDate;

public class InscripcionRepository {

//    public void insertarDesdeCSV(String rutaArchivo) {
//        EntityManager em = JPAUtil.getEntityManager();
//
//        try (CSVReader reader = new CSVReader(new FileReader(rutaArchivo))) {
//            String[] linea;
//            reader.readNext(); // salta cabecera
//
//            em.getTransaction().begin();
//
//            while ((linea = reader.readNext()) != null) {
//                Inscripcion inscripcion = new Inscripcion();
//                inscripcion.setIdInscripcion(Integer.parseInt(linea[1]));
//                inscripcion.setEstudiante(Integer.parseInt(linea[2]));
//                inscripcion.setCarrera(Integer.parseInt(linea[3]));
//                inscripcion.setFechaInsc(LocalDate.parse(linea[4]));
//                inscripcion.setFechaGrad(LocalDate.parse(linea[5]));
//
//                em.persist(inscripcion);
//            }
//
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            em.close();
//        }
//    }
}
