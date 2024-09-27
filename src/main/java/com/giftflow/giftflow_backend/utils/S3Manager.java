package com.giftflow.giftflow_backend.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Component
public class S3Manager {

    private final AmazonS3 client;

    public S3Manager() {
        this.client = AmazonS3Client.builder()
                .withRegion("us-east-1")
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("AKIASBMNKRX55UFTKJ4K", "UTb8wrq4MTNtbHyyhhbnSuSL7aaAQQyLGlfRauv8")))
                .build();
    }

    public void sendToS3(BufferedImage giftcardImage, String uuid) throws IOException{

        String fileName = uuid+".png";

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(giftcardImage, "png", os);
        byte[] buffer = os.toByteArray();

        InputStream is = new ByteArrayInputStream(buffer);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/png");
        metadata.setContentLength(buffer.length);

        this.client.putObject("giftflow-bucket", fileName, is, metadata);
    }



}
