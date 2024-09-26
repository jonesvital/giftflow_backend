package com.giftflow.giftflow_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;

import com.giftflow.giftflow_backend.dto.GiftCardDTO;
import com.giftflow.giftflow_backend.entities.Giftcard;
import com.giftflow.giftflow_backend.repositories.GiftcardRepository;

@Service
public class GiftCardService {
    
    @Autowired
    private GiftcardRepository giftcardRepository;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public GiftCardDTO createGiftCard(GiftCardDTO dto){

        Timestamp purchase_date = new Timestamp(dto.getPurchaseDate)


        Giftcard card = new Giftcard();
        card.setFromPerson(dto.getFromPersonName());
        card.setFromPersonPhone(dto.getFromPersonPhone());
        card.setToPerson(dto.getToPersonName());
        card.setToPersonPhone(dto.getToPersonPhone());
        card.setPurchaseDate(dateFormat.parse(dto.getPurchaseDate()));

    }


}
