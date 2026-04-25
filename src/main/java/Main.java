import dto.CarreraDTO;
import dto.EstudianteDTO;
import dto.InscripcionDTO;
import modelo.Carrera;
import modelo.Estudiante;
import modelo.Inscripcion;
import repository.CarreraRepository;
import repository.EstudianteRepository;
import repository.InscripcionRepository;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EstudianteRepository estudiante = new EstudianteRepository();
        CarreraRepository carrera = new CarreraRepository();
        InscripcionRepository inscripcion = new InscripcionRepository();

        estudiante.insertarDesdeCSV("src/main/resources/estudiantes.csv");
        carrera.insertarDesdeCSV("src/main/resources/carreras.csv");
        inscripcion.insertarDesdeCSV("src/main/resources/estudianteCarrera.csv");


//        System.out.println("Lista de carrreras: ");
//        List<CarreraDTO> carreraDTOS= carrera.buscarTodos();
//        for (CarreraDTO carre: carreraDTOS) {
//            System.out.println(carre);
//        }
//
//
//        System.out.println("Lista de estudiantes: ");
//        List<EstudianteDTO> estudianteDTOS= estudiante.buscarTodos();
//        for (EstudianteDTO est : estudianteDTOS){
//            System.out.println(est);
//        }
//
//
//        System.out.println("Lista de  inscripciones: ");
//        List<InscripcionDTO> inscripcionDTOS= inscripcion.buscarTodos();
//        for (InscripcionDTO insc : inscripcionDTOS){
//            System.out.println(insc);
//        }

        Carrera c1 = new Carrera("TUDAI2", 5);
        carrera.insertarCarrera(c1);

        Carrera c2 = new Carrera("TUDAI3", 5);
        carrera.insertarCarrera(c2);

        Estudiante e = new Estudiante("12345678", "Mati", "1", 1, 'a', "aaaa", "aaa");
        estudiante.insertarEstudiante(e);

        System.out.println(e.getIdEstudiante());
        System.out.println(c1.getIdCarrera());

        Inscripcion nuevaInsc = new Inscripcion(2022, 2026, 1, e, c1);
        Inscripcion segundaInsc = new Inscripcion(2022, 2026, 1, e, c2);


        inscripcion.matricularEstudiante(nuevaInsc);
        inscripcion.matricularEstudiante(segundaInsc);

        System.out.println(e.getIdEstudiante());
        System.out.println(c1.getIdCarrera());

        System.out.println(nuevaInsc.getEstudiante());
        System.out.println(segundaInsc.toString());

        System.out.println();
//        Inscripcion nueva = new Inscripcion()


    }
}
