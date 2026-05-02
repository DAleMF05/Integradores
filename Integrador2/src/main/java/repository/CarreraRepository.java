package repository;

import com.opencsv.CSVReader;
import dto.CarreraDTO;
import dto.CarreraInsDTO;
import dto.CarreraReporteDTO;
import dto.EstudianteDTO;
import factory.JPAUtil;
import jakarta.persistence.EntityManager;
import modelo.Carrera;
import modelo.Estudiante;

import java.io.FileReader;
import java.util.*;

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



    public List<CarreraReporteDTO> generarReporte() {

        EntityManager em = JPAUtil.getEntityManager();

        //se combinan inscriptos y graduados en esta lista
        List<CarreraReporteDTO> reporte = new ArrayList<>();

        //para traer todos los años en los que se inscribieron o graduaron se dividió en dos consultas
        List<CarreraReporteDTO> inscriptos = em.createQuery(
                "SELECT NEW dto.CarreraReporteDTO(" +
                        "c.nombre, " +
                        "i.fechaInsc, " +
                        "COUNT(i), " +
                        "0) " +
                        "FROM Inscripcion i JOIN i.carrera c " +
                        "GROUP BY c.nombre, i.fechaInsc " +
                        "ORDER BY c.nombre, i.fechaInsc",
                CarreraReporteDTO.class
        ).getResultList();

        reporte.addAll(inscriptos);



        List<CarreraReporteDTO> graduados = em.createQuery(
                "SELECT NEW dto.CarreraReporteDTO(" +
                        "c.nombre, " +
                        "i.fechaGrad, " +
                        "0, " +
                        "COUNT(i)) " +
                        "FROM Inscripcion i JOIN i.carrera c " +
                        "WHERE i.fechaGrad IS NOT NULL " +
                        "GROUP BY c.nombre, i.fechaGrad " +
                        "ORDER BY c.nombre, i.fechaGrad",
                CarreraReporteDTO.class
        ).getResultList();

        //busca si ya hay un dto para esa carrera y ese año, y si existe agrega los gradudados
        for (CarreraReporteDTO grad : graduados) {
            boolean existe = false;
            for (CarreraReporteDTO dto : reporte) {

                if (dto.getNombre().equals(grad.getNombre())
                        && dto.getAnio() == grad.getAnio()) {

                    dto.setGraduados(grad.getGraduados());

                    existe = true;
                    break;
                }
            }
            //si no existe agrega el año con solo los graduados en caso de que no haya inscriptos
            if (!existe) {

                reporte.add(grad);
            }
        }

        //ordena por nombre y año
        reporte.sort(
                Comparator.comparing(CarreraReporteDTO::getNombre)
                        .thenComparing(CarreraReporteDTO::getAnio)
        );

        em.close();

        return reporte;
    }

}
