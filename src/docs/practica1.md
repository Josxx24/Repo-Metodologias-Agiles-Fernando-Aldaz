# Práctica 1: Primera aplicación con Spring Boot

## Objetivos

Esta práctica tiene como propósito familiarizarnos con tecnologías esenciales para el desarrollo moderno de aplicaciones Java:

- Tener un primer contacto con **Spring Boot** desarrollando una aplicación básica "Hola Mundo".
- Utilizar **Thymeleaf** para la generación dinámica de vistas.
- Empezar a usar **Git** con pequeños commits en un repositorio personal de GitHub.
- Desplegar la aplicación en un contenedor **Docker** y publicarla en Docker Hub.

---

## Requisitos de Software

Antes de comenzar, es necesario contar con los siguientes componentes instalados:

- Java 17
- Spring Boot
- Thymeleaf
- Maven
- IntelliJ IDEA
---

## Configuración Inicial

### 1. GitHub

- Crear una cuenta en GitHub.
- Aceptar la invitación de GitHub Classroom.
- Acceder al repositorio creado con la URL:


### 2. Configuración de Git local

```bash
git config --global user.name "Nombre Apellido"
git config --global user.email "correo@ejemplo.com"
git clone https://github.com/mads-ua-23-24/springboot-demo-app-<usuario>.git
```

### 3. Dockerización de la Aplicación
Docker es un software de virtualización que permite gestionar contenedores de forma eficiente.  Estos contenedores son similares a las máquinas virtuales, pero comparten el sistema operativo, lo que los hace menos pesados y más rápidos.    

#### Proceso de Dockerización

##### Instalación de Docker:

- Instalar Docker Desktop (o Docker Engine en Linux).   
- Verificar la instalación con el comando docker version.   
- Creación de una cuenta en Docker Hub:
- Crear una cuenta en Docker Hub para almacenar y distribuir las imágenes Docker.    

##### Creación del Dockerfile:

- Crear un archivo llamado Dockerfile (sin extensión) en la raíz del proyecto.    
Añadir las instrucciones para construir la imagen Docker.    

##### Dockerfile

```
FROM openjdk:8-jdk-alpine
COPY target/*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/urandom","-jar","/app.jar"]
```
 
##### Construcción de la Imagen Docker:

Ejecutar el comando docker build desde la raíz del proyecto.   

```Bash

docker build -t <usuario-docker>/spring-boot-demoapp .
```

Verificar la creación de la imagen con **docker image ls**.

##### Ejecución de la Imagen en un Contenedor:

Ejecutar la imagen con el comando docker run.

```Bash

docker run -p 8080:8080 <usuario-docker>/spring-boot-demoapp
```
-p 8080:8080: Mapea el puerto 8080 del contenedor al puerto 8080 del host.    
Probar la aplicación en el navegador (ej., http://localhost:8080).    


## Estructura del Proyecto
El proyecto Spring Boot se compone de:

-  Carpeta controller: Define las rutas y lógica de presentación.
- Carpeta service: Contiene la lógica de negocio.
- Carpeta templates: Vistas HTML con Thymeleaf.
- Archivo pom.xml: Define dependencias y configuración Maven.
- Archivo application.properties: Configura aspectos como el nombre de la app y puerto.


---

## Desarrollo
### Lógica del Servicio

Aqui se encuentra la logica principal de la aplicacion como se presentara el mensaje y se analiza si la palabra es o no palindroma.
```java
package com.springboot.primerspringejemplo01.service;

import org.springframework.stereotype.Service;

@Service
public class SaludoService {

    public String saluda(String nombre) {
        return "Hola " + nombre + "!";
    }

    public boolean esPalindromo(String palabra) {
        if (palabra == null) return false;

        // Eliminar espacios y convertir a minúsculas
        String limpia = palabra.replaceAll("\\s+", "").toLowerCase();

        // Verificar si la cadena limpia está vacía
        if (limpia.isEmpty()) return false;

        // Verificar si es palíndromo
        String invertida = new StringBuilder(limpia).reverse().toString();
        return limpia.equals(invertida);
    }
}
```

### Controlador Saludo

El controlador SaludoController maneja la solicitud HTTP con la ruta /saludo/{nombre}, donde se recibe el nombre como parámetro en la URL y se devuelve el saludo como texto plano.
```java
package com.springboot.primerspringejemplo01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.springboot.primerspringejemplo01.service.SaludoService;

@Controller
public class SaludoController {

    @Autowired
    private SaludoService service;

    @RequestMapping("/saludo/{nombre}")
    public @ResponseBody String saludo(@PathVariable(value = "nombre") String nombre) {
        return service.saluda(nombre);
    }
}
```

### Controlador SaludorForm

El controlador SaludoControllerForm maneja el formulario en la ruta /saludoform. Este formulario permite al usuario ingresar su nombre, y al enviar el formulario, el nombre se valida y se muestra un saludo en la misma página. Si hay errores de validación, se regresa al formulario.
```java
package com.springboot.primerspringejemplo01.controller;

import com.springboot.primerspringejemplo01.service.SaludoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class SaludoControllerForm {

    @Autowired
    private SaludoService service;

    @GetMapping("/saludoform")
    public String saludoForm(Model model) {
        model.addAttribute("userData", new UserData());
        return "formRegistro";  // Regresa al formulario
    }

    @PostMapping("/saludoform")
    public String checkPersonInfo(@ModelAttribute @Valid UserData userData, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "formRegistro";  // Si hay errores de validación, regresa al formulario
        }
        model.addAttribute("mensaje", service.saluda(userData.getNombre()));
        return "saludo";  // Regresa a la vista de saludo
    }
}
```

### Controlador Saludo plantilla

El controlador SaludoControllerPlantilla maneja las solicitudes HTTP a la ruta /saludoplantilla/{nombre} y utiliza una plantilla Thymeleaf para mostrar el saludo. El mensaje se pasa a la plantilla y se presenta al usuario.
```java
package com.springboot.primerspringejemplo01.controller;

import com.springboot.primerspringejemplo01.service.SaludoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SaludoControllerPlantilla {

    @Autowired
    private SaludoService service;

    @RequestMapping("/saludoplantilla/{nombre}")
    public String saludo(@PathVariable(value="nombre") String nombre, Model model) {
        model.addAttribute("mensaje", service.saluda(nombre));
        return "saludo";  // Se llama a la plantilla 'saludo.html'
    }
}
```

## Tests

### Test de servicio

En esta prueba se verifica que el servicio SaludoService esté cargado correctamente y que el saludo se genere de la manera correcta.
```java
package com.springboot.primerspringejemplo01;

import com.springboot.primerspringejemplo01.service.SaludoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ServiceTest {

    
    SaludoService saludo;

    @Test
    public void contexLoads() throws Exception {
        assertThat(saludo).isNotNull();
    }

    @Test
    public void serviceSaludo() throws Exception {
        assertThat(saludo.saluda("Domingo")).isEqualTo("Hola Domingo");
    }
}
```

### Test MockMvc

Esta prueba simula las solicitudes HTTP y verifica que la respuesta sea la esperada.
```java
package com.springboot.primerspringejemplo01;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcTest {
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World")));
    }

    @Test
    public void postShoudReturnCorrectResponse() throws Exception {
        this.mockMvc.perform(post("/saludoform")
                        .param("nombre", "Domingo"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hola Domingo")));
    }
}
```

## Conclusión
Durante esta práctica se construyó y ejecutó una aplicación Spring Boot, se realizaron pruebas unitarias, se aplicó control de versiones con Git y se desplegó la aplicación en un contenedor Docker. También se añadió una funcionalidad personalizada a la aplicación base para reforzar la comprensión de los conceptos aprendidos.