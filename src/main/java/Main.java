import dto.CarreraDTO;
import dto.EstudianteDTO;
import modelo.Estudiante;
import repository.CarreraRepository;
import repository.EstudianteRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EstudianteRepository estudiante = new EstudianteRepository();
        CarreraRepository carrera = new CarreraRepository();

        estudiante.insertarDesdeCSV("src/main/resources/estudiante.csv");
        carrera.insertarDesdeCSV("src/main/resources/carrera.csv");

        System.out.println("Lista de carrreras: ");
        List<CarreraDTO> carreraDTOS= carrera.buscarTodos();
        for (CarreraDTO carre: carreraDTOS) {
            System.out.println(carre);
        }

        Estudiante e = new Estudiante("Ale","Lopez",40,'M',"23455667","Loberia","321");
        estudiante.insertarEstudiante(e);

        System.out.println("Lista de estudiantes: ");
        List<EstudianteDTO> estudianteDTOS= estudiante.buscarTodos();
        for (EstudianteDTO est : estudianteDTOS){
            System.out.println(est);
        }

    }
}
