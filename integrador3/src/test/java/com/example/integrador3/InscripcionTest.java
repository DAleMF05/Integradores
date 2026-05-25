package com.example.integrador3;

import com.example.integrador3.dto.InscripcionDTO;
import com.example.integrador3.exceptions.EstudianteExc.EstudianteNoEncontradoException;
import com.example.integrador3.exceptions.InscripcionExc.InscripcionConEstudianteInexistenteException;
import com.example.integrador3.service.InscripcionService;
import org.junit.*;
import org.junit.Assert;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Fail.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InscripcionTest {

    @Autowired
    private InscripcionService inscripcionService;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        System.out.println("==== Iniciando tests de InscripcionService ====");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {

        System.out.println("==== Finalizando tests de InscripcionService ====");
    }

    @Test
    public void testGetInscripcionById() {

        List<InscripcionDTO> inscripciones = inscripcionService.getAll();

        Assert.assertNotNull(inscripciones);
        Assert.assertTrue(inscripciones.size() > 0);

        InscripcionDTO inscripcion = inscripciones.getFirst();

        Assert.assertNotNull(inscripcion);
    }

    @Test
    public void testGuardarInscripcion() {

        InscripcionDTO inscripcion = new InscripcionDTO();

        inscripcion.setFechaInsc(2024);
        inscripcion.setFechaGrad(2030);
        inscripcion.setAntiguedad(1);
        inscripcion.setDniEstudiante("90033012");
        inscripcion.setNombreCarrera("TUDAI");

        InscripcionDTO resultado = inscripcionService.save(inscripcion);

        Assert.assertNotNull(resultado);
    }

    @Test
    public void testGuardarInscripcionConEstudianteInexistente() {

        try {

            InscripcionDTO inscripcion = new InscripcionDTO();

            inscripcion.setFechaInsc(2024);
            inscripcion.setFechaGrad(2030);
            inscripcion.setAntiguedad(1);
            inscripcion.setDniEstudiante("99999999");
            inscripcion.setNombreCarrera("TUDAI");

            inscripcionService.save(inscripcion);

            fail("Se esperaba una EstudianteNoEncontradoException");

        } catch (EstudianteNoEncontradoException e) {

            Assert.assertEquals(
                    "Estudiante no encontrado",
                    e.getMessage()
            );
        }
    }
}