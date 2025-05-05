package com.springboot.primerspringejemplo01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {
    @GetMapping("/hellorest")

    public String helloWorld() {
            return "hola mundo y su clase controller";
        }
}
