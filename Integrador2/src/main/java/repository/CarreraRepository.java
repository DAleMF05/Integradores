package repository;

import com.opencsv.CSVReader;
import dto.CarreraDTO;
import dto.CarreraInsDTO;
import dto.EstudianteDTO;
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
                carrera.setNombre(linea[1]);
                carrera.setDuracion(Integer.parseInt(linea[2]));

                em.persist(carrera);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }


    //dto
    public List<CarreraDTO> buscarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        List<CarreraDTO> carreras = em.createQuery("SELECT new dto.CarreraDTO(c.idCarrera, c.nombre, c.duracion) FROM Carrera c", CarreraDTO.class).getResultList();
        em.close();
        return carreras;
    }

    public void insertarCarrera(Carrera carrera) {
        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();

        Carrera existente = em.find(Carrera.class, carrera.getIdCarrera());

        if (existente == null) {
            em.persist(carrera);
        } else {
            System.out.println("Ya existe esa carrera");
        }

        em.getTransaction().commit();
        em.close();
    }

    public List<CarreraInsDTO> buscarPorInscriptos() {
        EntityManager em = JPAUtil.getEntityManager();

        List<CarreraInsDTO> carreraInscriptos = em.createQuery(
                "SELECT new dto.CarreraInsDTO(" +
                        "c.idCarrera, c.nombre, c.duracion, COUNT(i.idInscripcion)) " +
                        "FROM Carrera c JOIN c.inscripciones i " +
                        "GROUP BY c.idCarrera, c.nombre, c.duracion " +
                        "ORDER BY COUNT(i.idInscripcion) DESC",
                CarreraInsDTO.class
        ).getResultList();

        em.close();
        return carreraInscriptos;
    }

}
