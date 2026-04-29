package repository;

import com.opencsv.CSVReader;
import dto.EstudianteDTO;
import factory.JPAUtil;
import jakarta.persistence.EntityManager;
import modelo.Carrera;
import modelo.Estudiante;

import java.io.FileReader;
import java.util.List;

public class EstudianteRepository {

    public void insertarDesdeCSV(String rutaArchivo) {
        EntityManager em = JPAUtil.getEntityManager();

        try (CSVReader reader = new CSVReader(new FileReader(rutaArchivo))) {
            String[] linea;
            reader.readNext(); // salta cabecera

            em.getTransaction().begin();

            while ((linea = reader.readNext()) != null) {
                Estudiante estudiante = new Estudiante();
                estudiante.setDni(linea[0]);
                estudiante.setNombre(linea[1]);
                estudiante.setApellido(linea[2]);
                estudiante.setEdad(Integer.parseInt(linea[3]));
                estudiante.setGenero(linea[4].charAt(0));
                estudiante.setCiudad(linea[5]);
                estudiante.setNumLibretaUni(linea[6]);

                em.persist(estudiante);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void insertarEstudiante(Estudiante estudiante) {
        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();

        Estudiante existente = em.find(Estudiante.class, estudiante.getDni());

        if (existente == null) {
            em.persist(estudiante);
        } else {
            System.out.println("Ya existe ese estudiante");
        }

        em.getTransaction().commit();
        em.close();
    }


    //DTO
    public List<EstudianteDTO> buscarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        List<EstudianteDTO> estudiantes = em.createQuery("SELECT new dto.EstudianteDTO(e.dni, e.nombre, e.apellido, " +
                        "e.edad, e.genero, e.ciudad, e.numLibretaUni) FROM Estudiante e",
                            EstudianteDTO.class).getResultList();
        em.close();
        return estudiantes;
    }

    public List<EstudianteDTO> buscarTodosPorDNI() {
        EntityManager em = JPAUtil.getEntityManager();
        List<EstudianteDTO> estudiantesPorDNI = em.createQuery(
                "SELECT new dto.EstudianteDTO(e.dni, e.nombre, e.apellido, " +
                        "e.edad, e.genero, e.ciudad, e.numLibretaUni) " +
                        "FROM Estudiante e " +
                        "ORDER BY e.dni" ,
                EstudianteDTO.class).getResultList();
        em.close();
        return estudiantesPorDNI;
    }

    public EstudianteDTO buscarEstudiantePorLU(String lu) {

        EntityManager em = JPAUtil.getEntityManager();
        EstudianteDTO estudiantePorLU = em.createQuery(
                "SELECT new dto.EstudianteDTO(e.dni, e.nombre, e.apellido, " +
                        "e.edad, e.genero, e.ciudad, e.numLibretaUni) " +
                        "FROM Estudiante e " +
                        "WHERE e.numLibretaUni = :lu",
                EstudianteDTO.class)
                .setParameter("lu", lu)
                .getSingleResult();
        em.close();
        return estudiantePorLU;

    }

    public List<EstudianteDTO> buscarTodosPorGenero(char gen) {
        EntityManager em = JPAUtil.getEntityManager();
        List<EstudianteDTO> estudiantesPorGen= em.createQuery(
                "SELECT new dto.EstudianteDTO(e.dni, e.nombre, e.apellido, " +
                        "e.edad, e.genero, e.ciudad, e.numLibretaUni) " +
                        "FROM Estudiante e " +
                        "WHERE e.genero = :gen",
                EstudianteDTO.class)
                .setParameter("gen", gen).getResultList();
        em.close();
        return estudiantesPorGen;
    }

    public List<EstudianteDTO> buscarPorResidencia(String carrera, String ciudad) {
        EntityManager em = JPAUtil.getEntityManager();
        List<EstudianteDTO> estudiantes = em.createQuery(
                        "SELECT new dto.EstudianteDTO(e.dni, e.nombre, e.apellido," +
                                "e.edad, e.genero, e.ciudad, e.numLibretaUni) " +
                                "FROM Estudiante e " +
                                "JOIN e.inscripciones i " +
                                "JOIN i.carrera c " +
                                "WHERE e.ciudad = :ciudad " +
                                "AND c.nombre = :carrera",
                        EstudianteDTO.class)
                .setParameter("ciudad", ciudad)
                .setParameter("carrera", carrera)
                .getResultList();
        em.close();
        return estudiantes;
    }

}
