package com.USJT.BoletrustBack.Controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.USJT.BoletrustBack.models.Image;
import com.USJT.BoletrustBack.models.Readercod;
import com.USJT.BoletrustBack.models.VerifyBoleto;

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

             // Remover o prefixo 'data:image/jpeg;base64,' se existir
            String base64Image = image.getImg().split(",")[1];

            String cod = Readercod.read(base64Image);
            
            HashMap<String, String> result = VerifyBoleto.verify(cod);
            result.put("cod", cod);

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
