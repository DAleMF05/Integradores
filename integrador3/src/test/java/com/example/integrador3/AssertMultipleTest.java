package com.example.integrador3;

import com.example.integrador3.entities.Estudiante;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class AssertMultipleTest {

    @Test
    @DisplayName("Verificando varias caracteristicas a la vez")
    public void testVariasCaracteristicas() {
        Estudiante e = new Estudiante("2.234.528", "Mickey", "Mouse", 38, 'M', "Tandil", "LU999");
        Assertions.assertAll("Probando que se cumplen varias caracteristicas",
                () -> Assertions.assertEquals("2.234.528", e.getDni()),
                () -> Assertions.assertEquals(38, e.getEdad())
        );
    }

    @TestFactory
    Stream<DynamicTest> dynamicTestsFromEstudiantes() {
        List<Estudiante> testList = new ArrayList<>();
        Estudiante e1 = new Estudiante("26.150.235", "Juan", "Perez", 22, 'M', "Tandil", "LU001");
        Estudiante e2 = new Estudiante("27.280.234", "Pedro", "Garcia", 23, 'M', "Olavarria", "LU002");
        Estudiante e3 = new Estudiante("28.184.259", "Maria", "Lopez", 21, 'F', "Tandil", "LU003");
        testList.add(e1);
        testList.add(e2);
        testList.add(e3);
        return testList.stream()
                .map(est -> DynamicTest.dynamicTest("Testing: " + est.getNombre(), () -> {
                    Assertions.assertEquals(est.getEdad(), est.getEdad());
                }));
    }
}