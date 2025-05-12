package com.springboot.primerspringejemplo01.controller;

import jakarta.validation.constraints.Size;


public class UserData {

    @Size(min = 3, max = 30)

    String nombre;


    public void setNombre(String nombre) {

        this.nombre = nombre;

    }


    public String getNombre() {

        return nombre;

    }
}