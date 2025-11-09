package com.bookmx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class holamundo {
    @GetMapping("/api")
    public String holaMundo() {
        return "Hello, World!";
    }
}
