package com.springboot.primerspringejemplo01.service;

import org.springframework.stereotype.Service;


@Service

public class SaludoService {

    public String saluda(String nombre) {

        return "Hola " + nombre;

    }

}