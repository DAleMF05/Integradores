package com.example.integrador3;

import com.example.integrador3.entities.Estudiante;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class EstudianteTest {

    static List<Estudiante> estudiantes = new ArrayList<>();

    @BeforeClass
    public static void cargarEstudiantesEjemplo() throws Exception {

        Estudiante e1 = new Estudiante("26.150.235", "Juan", "Perez", 22, 'M', "Tandil", "LU001");
        Estudiante e2 = new Estudiante("27.280.234", "Pedro", "Garcia", 23, 'M', "Olavarria", "LU002");
        Estudiante e3 = new Estudiante("28.184.259", "Maria", "Lopez", 21, 'F', "Tandil", "LU003");

        estudiantes.add(e1);
        estudiantes.add(e2);
        estudiantes.add(e3);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("EstudianteTest -> AfterClass ");
    }

    @Test
    public void testAgregarEstudiante() {
        int cantAntes = estudiantes.size();
        estudiantes.add(new Estudiante("45.321.456", "Fermin", "Rodriguez", 25, 'M', "Azul", "LU004"));
        int cantDespues = estudiantes.size();
        Assert.assertEquals(cantAntes + 1, cantDespues);
    }

    @Test
    public void testAgregaEstudianteExacto() {
        Estudiante e = new Estudiante("45.321.456", "Fermin", "Rodriguez", 25, 'M', "Azul", "LU004");
        estudiantes.add(e);
        Estudiante eGuardado = estudiantes.get(estudiantes.size() - 1);
        Assert.assertSame("No se recibio el mismo estudiante que se esperaba", e, eGuardado);
    }
}