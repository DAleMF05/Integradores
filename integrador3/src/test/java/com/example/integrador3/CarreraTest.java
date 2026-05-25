package com.example.integrador3;

import com.example.integrador3.dto.CarreraDTO;
import com.example.integrador3.entities.Carrera;
import com.example.integrador3.exceptions.CarreraExc.CarreraNoEncontradaException;
import com.example.integrador3.service.CarreraService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Fail.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarreraTest {

    @Autowired
    private CarreraService carreraService;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        System.out.println("==== Iniciando tests de CarreraService ====");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {

        System.out.println("==== Finalizando tests de CarreraService ====");
    }

    @Test
    public void testGetCarreraById() {

        CarreraDTO carrera = carreraService.getById(1L);

        Assert.assertNotNull(carrera);
    }

    @Test
    public void testGetCarreraByIdInexistente() {

        try {

            carreraService.getById(999L);

            fail("Se esperaba una CarreraNoEncontradaException");

        } catch (CarreraNoEncontradaException e) {

            Assert.assertEquals(
                    "Carrera no encontrada",
                    e.getMessage()
            );
        }
    }

    @Test
    public void testGetAllCarreras() {

        List<CarreraDTO> carreras = carreraService.getAll();

        Assert.assertNotNull(carreras);
        Assert.assertTrue(carreras.size() > 0);
    }
}