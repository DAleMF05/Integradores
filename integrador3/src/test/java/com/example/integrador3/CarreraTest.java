package com.example.integrador3;

import com.example.integrador3.entities.Carrera;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import java.util.Random;

public class CarreraTest {

    static Carrera ejemplos[];
    static Carrera casoActual;

    @BeforeClass
    public static void cargarEjemplos() throws Exception {
        ejemplos = new Carrera[3];

        ejemplos[0] = new Carrera("TUDAI", 3);
        ejemplos[1] = new Carrera("Ingenieria de Sistemas", 5);
        ejemplos[2] = new Carrera("Licenciatura en Matematica", 4);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("CarreraTest -> AfterClass ");
    }

    @Before
    public void elegirUno() throws Exception {
        // generador de numeros aleatorios
        Random generadorAleatorios = new Random();
        // genera un numero entre 0 y longitud-1 y lo guarda en la variable numeroAleatorio
        int numeroAleatorio = generadorAleatorios.nextInt(ejemplos.length);
        casoActual = ejemplos[numeroAleatorio];
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("CarreraTest -> After");
    }

    @Test
    public void testDuracionValida() {
        int obtenido = casoActual.getDuracion();
        int esperado = 0;
        if (casoActual.getNombre().equals("TUDAI")) esperado = 3;
        else if (casoActual.getNombre().equals("Ingenieria de Sistemas")) esperado = 5;
        else if (casoActual.getNombre().equals("Licenciatura en Matematica")) esperado = 4;

        Assert.assertEquals(esperado, obtenido);
    }
}