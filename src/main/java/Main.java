import repository.CarreraRepository;
import repository.EstudianteRepository;

public class Main {
    public static void main(String[] args) {
        EstudianteRepository estudiante = new EstudianteRepository();
        CarreraRepository carrera = new CarreraRepository();

        estudiante.insertarDesdeCSV("src/main/resources/estudiante.csv");
        carrera.insertarDesdeCSV("src/main/resources/carrera.csv");

        System.out.println(estudiante.buscarTodos());
        System.out.println(carrera.buscarTodos());

    }
}
