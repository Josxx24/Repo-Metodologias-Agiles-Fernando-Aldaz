package com.springboot.primerspringejemplo01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWebController {

    //generar la ruta de la pagina web
    @RequestMapping("/helloweb")
    public String helloHandler() {
        return "hello";
    }
}
