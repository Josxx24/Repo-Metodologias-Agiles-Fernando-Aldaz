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
        // Asegúrate de pasar un objeto UserData vacío al modelo
        model.addAttribute("userData", new UserData());
        return "formRegistro";  // Regresa al formulario
    }

    @PostMapping("/saludoform")
    public String checkPersonInfo(@ModelAttribute @Valid UserData userData, BindingResult bindingResult, Model model) {
        // Si hay errores de validación, regresa al formulario
        if (bindingResult.hasErrors()) {
            return "formRegistro";
        }

        // Si no hay errores, pasa el mensaje al modelo y muestra la vista de saludo
        model.addAttribute("mensaje", service.saluda(userData.getNombre()));
        return "saludo";  // Regresa a la vista de saludo
    }
}