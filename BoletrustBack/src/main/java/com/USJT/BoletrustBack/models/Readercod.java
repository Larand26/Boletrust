package com.USJT.BoletrustBack.models;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

public class Readercod {
    public static String read(String base64Image) {
        try {
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

        } catch (NotFoundException e) {
            System.out.println("Erro ao ler o código de barras (NotFoundException): " + e.getMessage());
            return "Erro ao ler o código de barras";
        } catch (IOException e) {
            System.out.println("Erro ao ler o código de barras (IOException): " + e.getMessage());
            return "Erro ao ler o código de barras";
        }
    }
}
