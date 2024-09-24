package com.giftflow.giftflow_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giftflow.giftflow_backend.dto.BeaultyServiceDTO;
import com.giftflow.giftflow_backend.services.BeaultyServiceService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/beauty-services")
public class BeautyServiceController {

    @Autowired
    private BeaultyServiceService beautyServiceService;
    

    @PostMapping()    
    public BeaultyServiceDTO getMethodName(@RequestBody BeaultyServiceDTO dto) {
        return beautyServiceService.createService(dto);
    }
    
    
}
