package com.USJT.BoletrustBack.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
public class HelloWorldController{
    
    @CrossOrigin(origins = "*")
    @GetMapping
    public String helloWorld(){
        return "Hello World!";
    }
}