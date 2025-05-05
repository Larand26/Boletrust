package com.USJT.BoletrustBack.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-word")
public class HelloWordConller{
    
    @CrossOrigin(origins = "*")
    @GetMapping
    public String helloWord(){
        return "Hello Word!";
    }
}