package com.springboot.primerspringejemplo01;

import com.springboot.primerspringejemplo01.service.SaludoService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MockServiceTest {


    private SaludoService saludoService;

    @Test
    public void testSaluda() {
        String result = saludoService.saluda("Domingo");
        assertThat(result).isEqualTo("Hola Domingo");
    }
}
