package com.USJT.BoletrustBack.models;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class VerifyBoleto {

    public static HashMap<String, String> verify(String cod) {
        HashMap<String, String> result = new HashMap<>();

        try {
            // Lê do classpath (src/main/resources/json/bancos.json)
            InputStream input = VerifyBoleto.class.getClassLoader().getResourceAsStream("json/bancos.json");
            if (input == null) throw new Exception("Arquivo bancos.json não encontrado no classpath");

            JSONParser parser = new JSONParser();
            JSONObject bancos = (JSONObject) parser.parse(new InputStreamReader(input));

            String codBanco = cod.substring(0, 3);
            JSONObject dadosBanco = (JSONObject) bancos.get(codBanco);

            if (dadosBanco != null) {
                result.put("status", "success");
                result.put("banco", (String) dadosBanco.get("nome"));
                result.put("logo", (String) dadosBanco.get("logo"));
            } else {
                result.put("status", "error");
                result.put("message", "Código de banco não encontrado");
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", "error");
            result.put("message", "Erro ao processar o código de barras: " + e.getMessage());
        }

        return result;
    }
}
