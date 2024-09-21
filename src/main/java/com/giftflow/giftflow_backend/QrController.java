package com.giftflow.giftflow_backend;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


@RestController
@RequestMapping("/giftflow")
public class QrController {
    
    @GetMapping(produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> getMethodName() throws WriterException, IOException, FontFormatException {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/static/PlayfairDisplay-Italic-VariableFont_wght.ttf"));
        ge.registerFont(font);

        QRCodeWriter qrWriter = new QRCodeWriter();
        BitMatrix qrCode = qrWriter.encode("Andreza", BarcodeFormat.QR_CODE, 100, 100);
        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(qrCode);

        BufferedImage template = ImageIO.read(new File("src/main/resources/static/template.png"));
        Graphics g = template.getGraphics();
        g.drawImage(qrImage, 50, 200, null);

        g.setColor(Color.black);
        g.setFont(new Font("Playfair Display", Font.ITALIC, 30));
        g.drawString("Andreza", 250, 80);

        g.setFont(new Font("Playfair Display", Font.ITALIC, 30));
        g.drawString("Micropigmentação", 200, 180);

        ResponseEntity<BufferedImage> responseEntity = ResponseEntity.ok().body(template);

        return responseEntity;
    }


    

}
