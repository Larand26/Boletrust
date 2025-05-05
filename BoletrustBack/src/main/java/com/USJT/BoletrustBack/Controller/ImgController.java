package com.USJT.BoletrustBack.Controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.USJT.BoletrustBack.models.Image;

@RestController
@RequestMapping("/img-verify")
public class ImgController {

    @CrossOrigin(origins = "*")
    @PostMapping
    public HashMap<String, String> uploadImage(@RequestBody Image image) {
        
        try {
            HashMap<String,String> result = new HashMap<>();
            result.put("message", "foi");
            return result;
        } catch (Exception e) {
            HashMap<String,String> error = new HashMap<>();
            error.put("message", "Houve um erro");
            error.put("Erro: ", e.toString());
            return error;
        }
    }
    
    
}
