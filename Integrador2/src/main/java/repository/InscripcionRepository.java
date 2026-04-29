package repository;

import com.opencsv.CSVReader;
import dto.EstudianteDTO;
import dto.InscripcionDTO;
import factory.JPAUtil;
import jakarta.persistence.EntityManager;
import modelo.Carrera;
import modelo.Estudiante;
import modelo.Inscripcion;

import java.io.FileReader;
import java.time.LocalDate;
import java.util.List;

public class InscripcionRepository {


    public void insertarDesdeCSV(String rutaArchivo) {
        EntityManager em = JPAUtil.getEntityManager();

       try (CSVReader reader = new CSVReader(new FileReader(rutaArchivo))) {
            String[] linea;
          reader.readNext(); // salta cabecera

            em.getTransaction().begin();

           while ((linea = reader.readNext()) != null) {
                Inscripcion inscripcion = new Inscripcion();

               Estudiante estudiante = em.createQuery(
                               "SELECT e FROM Estudiante e WHERE e.dni = :dni",
                               Estudiante.class
                       ).setParameter("dni", linea[1])
                       .getSingleResult();
               inscripcion.setEstudiante(estudiante);
               Carrera carrera = em.find(Carrera.class, Integer.parseInt(linea[2]));
               inscripcion.setCarrera(carrera);
                inscripcion.setFechaInsc(Integer.parseInt(linea[3]));
               inscripcion.setFechaGrad(Integer.parseInt(linea[4]));
               inscripcion.setAntiguedad(Integer.parseInt(linea[5]));

               em.persist(inscripcion);
           }

           em.getTransaction().commit();
        } catch (Exception e) {
           e.printStackTrace();
       } finally {
           em.close();
        }
    }

    public List<InscripcionDTO> buscarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        List<InscripcionDTO> inscripcionDTOS = em.createQuery("SELECT new dto.InscripcionDTO(i.idInscripcion, e.dni, c.idCarrera, i.fechaInsc, i.fechaGrad, i.antiguedad ) FROM Inscripcion i JOIN i.estudiante e JOIN i.carrera c ", InscripcionDTO.class).getResultList();
        em.close();
        return inscripcionDTOS;
    }

    public void matricularEstudiante(Inscripcion nueva) {
        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();

        Estudiante est = em.find(Estudiante.class, nueva.getEstudiante().getIdEstudiante());
        Carrera car = em.find(Carrera.class, nueva.getCarrera().getIdCarrera());

        nueva.setEstudiante(est);
        nueva.setCarrera(car);

//        Inscripcion existente = em.find(Inscripcion.class, estudiante.getDni(), carrera.getIdCarrera());

//        if (existente == null) {
//            em.persist(estudiante);
//        } else {
//            System.out.println("Ya existe ese estudiante");
//        }
        em.persist(nueva);

        em.getTransaction().commit();
        em.close();
    }

}
