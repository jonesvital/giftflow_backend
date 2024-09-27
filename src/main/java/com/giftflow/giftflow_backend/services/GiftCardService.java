package com.giftflow.giftflow_backend.services;

import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giftflow.giftflow_backend.dto.GiftCardDTO;
import com.giftflow.giftflow_backend.entities.BeaultyService;
import com.giftflow.giftflow_backend.entities.Giftcard;
import com.giftflow.giftflow_backend.repositories.BeaultyServiceRepository;
import com.giftflow.giftflow_backend.repositories.GiftcardRepository;
import com.giftflow.giftflow_backend.utils.GiftcardFactory;
import com.giftflow.giftflow_backend.utils.S3Manager;
import com.google.zxing.WriterException;

@Service
public class GiftCardService {
    
    @Autowired
    private GiftcardRepository giftcardRepository;

    @Autowired
    private BeaultyServiceRepository beaultyServiceRepository;

    @Autowired
    private GiftcardFactory giftcardFactory;

    @Autowired
    private S3Manager s3Manager;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public BufferedImage createGiftCard(GiftCardDTO dto) throws ParseException, WriterException, IOException, FontFormatException{

        BeaultyService beaultyService = beaultyServiceRepository.findById(dto.getServiceId()).get();

        String uuid = UUID.randomUUID().toString();

        BufferedImage giftcardImage = giftcardFactory.generate(dto.getToPersonName(), beaultyService.getName(), uuid);
        
        s3Manager.sendToS3(giftcardImage, uuid);

        Timestamp purchaseDate = new Timestamp(this.dateFormat.parse(dto.getPurchaseDate()).getTime());        

        Giftcard card = new Giftcard();
        card.setFromPerson(dto.getFromPersonName());
        card.setFromPersonPhone(dto.getFromPersonPhone());
        card.setToPerson(dto.getToPersonName());
        card.setToPersonPhone(dto.getToPersonPhone());
        card.setPurchaseDate(purchaseDate);
        card.setService(beaultyService);

        giftcardRepository.save(card);

        return giftcardImage;
        
    }


}
