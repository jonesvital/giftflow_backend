package com.giftflow.giftflow_backend.services;

import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

        UUID uuid = UUID.randomUUID();

        BufferedImage giftcardImage = giftcardFactory.generate(dto.getToPersonName(), beaultyService.getName(), uuid.toString());
        
        String s3FileUri = s3Manager.sendToS3(giftcardImage, uuid.toString());      

        Giftcard card = new Giftcard();
        card.setFromPerson(dto.getFromPersonName());
        card.setFromPersonPhone(dto.getFromPersonPhone());
        card.setToPerson(dto.getToPersonName());
        card.setToPersonPhone(dto.getToPersonPhone());
        card.setPurchaseDate(new Date());
        card.setService(beaultyService);
        card.setGiftcardUuid(uuid);
        card.setS3Uri(s3FileUri);

        giftcardRepository.save(card);

        return giftcardImage;
        
    }

    public List<GiftCardDTO> listarGiftcards(){
        
        List<Giftcard> giftcards = giftcardRepository.findAll();

        List<GiftCardDTO> dtos = new ArrayList<>();
        
        giftcards.forEach((gc) -> {

            String serviceDate = null;

            if(gc.getServiceDate() != null){
                serviceDate = dateFormat.format(gc.getServiceDate());
            }

            GiftCardDTO dto = new GiftCardDTO(
                gc.getId(), 
                gc.getFromPerson(), 
                gc.getFromPersonPhone(), 
                gc.getToPerson(), 
                gc.getToPersonPhone(), 
                gc.getService().getName(), 
                gc.getService().getId(), 
                dateFormat.format(gc.getPurchaseDate()), 
                serviceDate,
                null,
                null);

            dtos.add(dto);

        });

        return dtos;

    }

    public void deleteGiftcard(Integer id){
        giftcardRepository.deleteById(id);
    }

    public BufferedImage getImageByUUID(String uuid) throws IOException{
        BufferedImage image = s3Manager.getS3File(uuid);
        return image;
    }

    public GiftCardDTO getGiftcardByUUID(String uuid) {

        Giftcard giftcard = giftcardRepository.getGiftcardByUUID(UUID.fromString(uuid));

        String serviceDate = null;

        if(giftcard.getServiceDate() != null){
            serviceDate = dateFormat.format(giftcard.getServiceDate());
        }

        GiftCardDTO dto = new GiftCardDTO(
            giftcard.getId(), 
            giftcard.getFromPerson(), 
            giftcard.getFromPersonPhone(), 
            giftcard.getToPerson(), 
            giftcard.getToPersonPhone(), 
            giftcard.getService().getName(), 
            giftcard.getService().getId(), 
            dateFormat.format(giftcard.getPurchaseDate()), 
            serviceDate,
            giftcard.getGiftcardUuid().toString(),
            giftcard.getS3Uri());

        return dto;
    }

}
