package com.giftflow.giftflow_backend.controllers;

import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giftflow.giftflow_backend.dto.GiftCardDTO;
import com.giftflow.giftflow_backend.services.GiftCardService;
import com.google.zxing.WriterException;



@RestController
@RequestMapping("/giftcard")
public class GiftCardController {

    @Autowired
    private GiftCardService giftcardService;
    
    @PostMapping(produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> createGifcard(@RequestBody GiftCardDTO dto) throws ParseException, WriterException, IOException, FontFormatException {
        BufferedImage giftcardImage = giftcardService.createGiftCard(dto);
        return ResponseEntity.ok().body(giftcardImage);
    }

    @GetMapping()
    public List<GiftCardDTO> listGiftcards() {
        return giftcardService.listarGiftcards();
    }
    
    @DeleteMapping("/{id}")
    public void deleteGiftcard(@PathVariable("id") Integer id){
        giftcardService.deleteGiftcard(id);
    }
    
}
