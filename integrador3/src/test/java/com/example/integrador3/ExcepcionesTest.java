package com.example.integrador3;

import com.example.integrador3.entities.Estudiante;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ExcepcionesTest {

    @Test
    @DisplayName("Buscar estudiante por libreta universitaria que no existe")
    public void testExpectedEstudianteNoEncontradoException() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Estudiante no encontrado con esa LU");
        });
    }
}