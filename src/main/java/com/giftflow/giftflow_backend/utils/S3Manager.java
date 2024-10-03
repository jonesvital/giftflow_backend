package com.giftflow.giftflow_backend.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;

@Component
public class S3Manager {

    @Value("${aws.credentials.accessKey}")
    private String accessKey;

    @Value("${aws.credentials.secretKey}")
    private String secretKey;

    @Value("${aws.s3.bucketName}")
    private String bucketName;
    
    @Value("${aws.s3.region}")
    private String region;

    public String sendToS3(BufferedImage giftcardImage, String uuid) throws IOException{

        AmazonS3 client = AmazonS3Client.builder()
        .withRegion(region)
        .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
        .build();

        String fileName = uuid+".png";

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(giftcardImage, "png", os);
        byte[] buffer = os.toByteArray();

        InputStream is = new ByteArrayInputStream(buffer);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/png");
        metadata.setContentLength(buffer.length);

        client.putObject(bucketName, fileName, is, metadata);

        return "s3://"+bucketName+"/"+fileName;
    }

    public BufferedImage getS3File(String uuid) throws IOException{

        AmazonS3 client = AmazonS3Client.builder()
        .withRegion(region)
        .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
        .build();

        String key = uuid+".png";
        S3Object o = client.getObject(bucketName, key);

        BufferedImage image = ImageIO.read(o.getObjectContent());

        return image;
    }



}
