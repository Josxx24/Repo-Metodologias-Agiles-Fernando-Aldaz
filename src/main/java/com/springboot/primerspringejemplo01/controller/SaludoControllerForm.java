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
        if (bindingResult.hasErrors()) {
            return "formRegistro";
        }

        String nombre = userData.getNombre();
        model.addAttribute("mensaje", service.saluda(nombre));
        model.addAttribute("esPalindromo", service.esPalindromo(nombre));

        return "saludo";
    }
}