package com.example.pruebaLoginGoogle.Controllers;

import org.springframework.web.bind.annotation.GetMapping;


@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/")
    public String Principal(){
        return ("/principal");
    }

    @GetMapping("/login")
    public String Login(){
        return "/login";
    }


}
