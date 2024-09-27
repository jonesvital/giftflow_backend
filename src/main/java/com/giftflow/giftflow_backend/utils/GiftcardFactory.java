package com.giftflow.giftflow_backend.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Component
public class GiftcardFactory {
    
    public BufferedImage generate(String toPersonName, String serviceName, String uuid) throws WriterException, IOException, FontFormatException {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/static/PlayfairDisplay-Italic-VariableFont_wght.ttf"));
        ge.registerFont(font);

        QRCodeWriter qrWriter = new QRCodeWriter();
        BitMatrix qrCode = qrWriter.encode(uuid, BarcodeFormat.QR_CODE, 100, 100);
        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(qrCode);

        BufferedImage template = ImageIO.read(new File("src/main/resources/static/template.png"));
        Graphics g = template.getGraphics();
        g.drawImage(qrImage, 50, 200, null);

        g.setColor(Color.black);
        g.setFont(new Font("Playfair Display", Font.ITALIC, 30));
        g.drawString(toPersonName, 250, 80);

        g.setFont(new Font("Playfair Display", Font.ITALIC, 30));
        g.drawString(serviceName, 200, 180);

        return template;
    }

}
