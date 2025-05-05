package com.USJT.BoletrustBack.models;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Base64;

public class Readercod {
    public static String read(String base64Image) {
        try {
            System.out.println("Entrou no ReaderCod");

            // Remove o prefixo se tiver (ex: "data:image/png;base64,")
            if (base64Image.contains(",")) {
                base64Image = base64Image.split(",")[1];
            }

            // Decodifica a imagem
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
            BufferedImage image = ImageIO.read(bis);

            // Converte para formato lido pelo ZXing
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            // Tenta decodificar
            Result result = new MultiFormatReader().decode(bitmap);

            return result.getText();

        } catch (Exception e) {
            System.out.println("Erro ao ler o código de barras: " + e.getMessage());
            return "Erro ao ler o código de barras";
        }
    }
}
