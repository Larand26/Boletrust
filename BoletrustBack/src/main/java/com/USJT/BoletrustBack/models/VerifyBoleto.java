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
            String dv = cod.substring(4, 5);
            String vencimento = cod.substring(5, 9);
            String valor = cod.substring(9, 19);
            String convenio = cod.substring(19, 23);
            String agenciaRel = cod.substring(30, 34);
            String conta = cod.substring(34, 42);
            String carteira =  cod.substring(42, 43);

            //Verifica o DV
            boolean dvValido = verifyDV(cod, dv);
            

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
            result.put("status", "error");
            result.put("message", "Erro ao processar o código de barras: " + e.getMessage());
        }

        return result;
    }

    private static boolean verifyDV(String cod, String dv) {
        try {
            // Remove o DV (posição 4)
            String semDV = cod.substring(0, 4) + cod.substring(5);
            int peso = 2;
            int soma = 0;

            // Percorre da direita para a esquerda
            for (int i = semDV.length() - 1; i >= 0; i--) {
                int num = Character.getNumericValue(semDV.charAt(i));
                soma += num * peso;
            peso++;
            if (peso > 9) peso = 2;
        }

        int resto = soma % 11;
        int dvCalculado = 11 - resto;
        if (dvCalculado == 0 || dvCalculado == 10 || dvCalculado == 11) dvCalculado = 1;
        System.out.println(dv.equals(String.valueOf(dvCalculado))?"Está correto":"Está errado");
        return dv.equals(String.valueOf(dvCalculado));
    } catch (Exception e) {
        return false;
    }
}
}
