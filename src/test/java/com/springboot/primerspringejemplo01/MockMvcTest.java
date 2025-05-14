package com.springboot.primerspringejemplo01;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    // Test para comprobar que la página principal devuelve el mensaje "Hello World"
    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())  // Verifica que la respuesta sea 200 OK
                .andExpect(content().string(containsString("Hello World")));  // Verifica que "Hello World" esté en el contenido
    }

    // Test para hacer una petición POST con datos del formulario y comprobar la respuesta
    @Test
    public void postShouldReturnCorrectResponse() throws Exception {
        this.mockMvc.perform(post("/saludoform")
                        .param("nombre", "Domingo"))  // Enviar el parámetro 'nombre' con valor "Domingo"
                .andExpect(status().isOk())  // Verifica que la respuesta sea 200 OK
                .andExpect(content().string(containsString("Hola Domingo")));  // Verifica que la respuesta contiene "Hola Domingo"
    }
}
