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
