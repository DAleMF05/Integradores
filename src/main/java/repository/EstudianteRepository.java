package repository;

import com.opencsv.CSVReader;
import dto.EstudianteDTO;
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
