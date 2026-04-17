package repository;

import com.opencsv.CSVReader;
import factory.JPAUtil;
import jakarta.persistence.EntityManager;
import modelo.Carrera;
import modelo.Estudiante;

import java.io.FileReader;
import java.util.List;

public class CarreraRepository {

    public void insertarDesdeCSV(String rutaArchivo) {
        EntityManager em = JPAUtil.getEntityManager();

        try (CSVReader reader = new CSVReader(new FileReader(rutaArchivo))) {
            String[] linea;

            // revisar salto de linea
            reader.readNext(); // salta cabecera

            em.getTransaction().begin();

            while ((linea = reader.readNext()) != null) {
                Carrera carrera = new Carrera();
                carrera.setNombre(linea[0]);

                em.persist(carrera);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }


    public List<Carrera> buscarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Carrera> carreras = em.createQuery("SELECT c FROM Carrera c", Carrera.class).getResultList();
        em.close();
        return carreras;
    }
}
