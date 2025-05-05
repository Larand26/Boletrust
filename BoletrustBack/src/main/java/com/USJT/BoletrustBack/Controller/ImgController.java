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
            if (image.getImg() == null || image.getImg().isEmpty()) {
                HashMap<String, String> errorResponse = new HashMap<>();
                errorResponse.put("status", "erro");
                errorResponse.put("message", "Imagem não colocada");
                return errorResponse;
            }
            HashMap<String,String> result = new HashMap<>();
            result.put("status", "sucesso");
            result.put("message", "foi");
            return result;
        } catch (Exception e) {
            HashMap<String,String> errorResponse = new HashMap<>();
            errorResponse.put("status", "erro");
            errorResponse.put("message", "Houve um erro");
            errorResponse.put("Erro: ", e.toString());
            return errorResponse;
        }
    }
    
    
}
