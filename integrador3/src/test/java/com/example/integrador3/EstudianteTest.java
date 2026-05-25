package com.example.integrador3;

import com.example.integrador3.dto.EstudianteDTO;
import com.example.integrador3.exceptions.EstudianteExc.EstudianteNoEncontradoException;
import com.example.integrador3.exceptions.EstudianteExc.LUNoEncontradaException;
import com.example.integrador3.service.EstudianteService;
import org.junit.Assert;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Fail.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EstudianteTest {

    @Autowired
    private EstudianteService estudianteService;


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        System.out.println("==== Iniciando tests de EstudianteService ====");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {

        System.out.println("==== Finalizando tests de EstudianteService ====");
    }


    @Test
    public void testGetById() {
        System.out.println("test 1");
        EstudianteDTO estudiante = estudianteService.getById(1L);

        Assert.assertNotNull(estudiante);
    }

    @Test
    public void testGetByIdInexistente() {

        try {
            estudianteService.getById(999L);
//            Assert.fail("Se esperaba una EstudianteNoEncontradoException");
            fail("Se esperaba una EstudianteNoEncontradoException");
        } catch (EstudianteNoEncontradoException e) {
            Assert.assertEquals(
                    "Estudiante no encontrado con id: 999",
                    e.getMessage()
            );
        }
    }

    @Test
    public void testGetByLibretaUniInexistente() {

        try {

            estudianteService.getByLibretaUni("LU999");

            fail("Se esperaba una LUNoEncontradaException");

        } catch (LUNoEncontradaException e) {

            Assert.assertEquals(
                    "Estudiante no encontrado con esa LU",
                    e.getMessage()
            );
        }
    }
}