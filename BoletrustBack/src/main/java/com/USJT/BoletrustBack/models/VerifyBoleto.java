package com.USJT.BoletrustBack.models;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.HashMap;

public class VerifyBoleto {

    public static HashMap<String, String> verify(String cod) {
        HashMap<String, String> result = new HashMap<>();

        try {

            // Verifica se o código de barras tem 44 caracteres ou é nulo
            if (cod == null || cod.isEmpty() || cod.length() != 44) {
                result.put("status", "error");
                result.put("message", "Código de barras inválido ou faltando digitos");
                return result;
            }

            // Lê do classpath (src/main/resources/json/bancos.json)
            InputStream input = VerifyBoleto.class.getClassLoader().getResourceAsStream("json/bancos.json");
            if (input == null) throw new Exception("Arquivo bancos.json não encontrado no classpath");

            JSONParser parser = new JSONParser();
            JSONObject bancos = (JSONObject) parser.parse(new InputStreamReader(input));

            String codBanco = cod.substring(0, 3);
            String dv = cod.substring(4, 5);
            String vencimento = cod.substring(5, 9);
            String valor = cod.substring(9, 19);

            //Verifica o DV
            boolean dvValido = verifyDV(cod, dv);

            // Verifica o vencimento
            String vencimentoDate = getVencimento(Integer.parseInt(vencimento));

            // Verifica o valor
            double valorDouble = Double.parseDouble(valor) / 100;

            JSONObject dadosBanco = (JSONObject) bancos.get(codBanco);

            if (dadosBanco != null) {
                result.put("status", "success");
                result.put("banco", (String) dadosBanco.get("nome"));
                result.put("logo", (String) dadosBanco.get("logo"));
                result.put("vencimento", vencimentoDate);
                result.put("valor", String.format("%.2f", valorDouble));
                result.put("dvValido", String.valueOf(dvValido));
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

    private static Date getVencimento(String vencimento) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVencimento'");
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
        return dv.equals(String.valueOf(dvCalculado));
    } catch (Exception e) {
        return false;
    }
}

    public static String getVencimento(int venc) {
        try {
            // Exemplo de cálculo de vencimento a partir de um fator de vencimento (dias desde 07/10/1997)
            java.util.Calendar base = java.util.Calendar.getInstance();
            base.set(1997, java.util.Calendar.OCTOBER, 7, 0, 0, 0);
            base.set(java.util.Calendar.MILLISECOND, 0);
            base.add(java.util.Calendar.DATE, venc);
            String dateOfVenc = String.format("%02d/%02d/%04d", base.get(java.util.Calendar.DAY_OF_MONTH), base.get(java.util.Calendar.MONTH) + 1, base.get(java.util.Calendar.YEAR));
            return dateOfVenc;
        } catch (Exception e) {
            return null;
        }
    }

}
