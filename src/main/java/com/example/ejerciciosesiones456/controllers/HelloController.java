package com.example.ejerciciosesiones456.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/saludar")
    public String saludar(){
        return "Hola mundo desde OB Spring";
    }

}
