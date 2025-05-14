package com.springboot.primerspringejemplo01;

import com.springboot.primerspringejemplo01.service.SaludoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ServiceTest {

    @Autowired
    SaludoService saludo;

    @Test
    public void contexLoads() throws Exception {
        assertThat(saludo).isNotNull();
    }

    @Test
    public void serviceSaludo() throws Exception {
        assertThat(saludo.saluda("Domingo")).isEqualTo("Hola Domingo!");
    }

    // Test para el método esPalindromo
    @Test
    public void testEsPalindromo_verdadero() {
        assertThat(saludo.esPalindromo("Ana")).isTrue();  // "Ana" es un palíndromo
    }

    @Test
    public void testEsPalindromo_falso() {
        assertThat(saludo.esPalindromo("Carlos")).isFalse();  // "Carlos" no es un palíndromo
    }

    @Test
    public void testEsPalindromo_palabra_vacia() {
        assertThat(saludo.esPalindromo("")).isFalse();  // Una cadena vacía no es palíndroma
    }

    @Test
    public void testEsPalindromo_con_espacios() {
        assertThat(saludo.esPalindromo("A man a plan a canal Panama")).isTrue();  // Con espacios es un palíndromo
    }

    @Test
    public void testEsPalindromo_mayusculas_minusculas() {
        assertThat(saludo.esPalindromo("RaceCar")).isTrue();  // "RaceCar" es un palíndromo ignorando mayúsculas y minúsculas
    }
}
