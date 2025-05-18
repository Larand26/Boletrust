package com.USJT.BoletrustBack.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import com.USJT.BoletrustBack.models.VerifyBoleto;

@RestController
@RequestMapping("/txt-verify")
public class TxtController {
    @CrossOrigin(origins = "*")
    @PostMapping
    public HashMap<String, String> upTxt(@RequestBody String cod) {
        try {
            if (cod == null || cod.isEmpty()) {
                HashMap<String, String> errorResponse = new HashMap<>();
                errorResponse.put("status", "erro");
                errorResponse.put("message", "Código de barras não colocado");
                return errorResponse;
            }

            HashMap<String, String> result = VerifyBoleto.verify(cod);
            result.put("cod", cod);

            return result;
        } catch (Exception e) {
            HashMap<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "erro");
            errorResponse.put("message", "Houve um erro");
            errorResponse.put("Erro: ", e.toString());
            return errorResponse;
        }
    }
}
