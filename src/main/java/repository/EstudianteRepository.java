package repository;

import com.opencsv.CSVReader;
import factory.JPAUtil;
import jakarta.persistence.EntityManager;
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
                estudiante.setIdEstudiante(Integer.parseInt(linea[1]));
                estudiante.setNombre(linea[2]);
                estudiante.setApellido(linea[3]);
                estudiante.setEdad(Integer.parseInt(linea[4]));
                estudiante.setGenero(linea[5].charAt(0));
                estudiante.setDni(linea[6]);
                estudiante.setCiudad(linea[7]);
                estudiante.setNumLibretaUni(linea[8]);
//                estudiante.setInscripciones();

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

//        if(estudiante.getIdEstudiante() == null) {
//            em.persist(estudiante);
//        } else {
//            em.merge(estudiante);
//        }
        em.persist(estudiante);
    }

    public List<Estudiante> buscarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Estudiante> estudiantes = em.createQuery("SELECT e FROM Estudiante e", Estudiante.class).getResultList();
        em.close();
        return estudiantes;
    }

//    public List<DireccionDTO> direccion_de_persona(String nombre) {
//        EntityManager em = JPAUtil.getEntityManager();
//        List<DireccionDTO> direcciones = new ArrayList<>();
//
//        try {
//            direcciones = em.createQuery(
//                            "SELECT new micro.example.dto.DireccionDTO(d.ciudad, d.calle, d.numero, d.codigoPostal) " +
//                                    "FROM Direccion d JOIN d.personas p " +
//                                    "WHERE p.nombre = :nombre",
//                            DireccionDTO.class
//                    )
//                    .setParameter("nombre", nombre)
//                    .getResultList();
//
//            if (direcciones.isEmpty()) {
//                System.out.println("No existe persona con ese nombre");
//            }
//
//        } catch (Exception e) {
//            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
//        } finally {
//            em.close();
//        }
//
//        return direcciones;
//    }
}
