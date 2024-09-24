package com.giftflow.giftflow_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giftflow.giftflow_backend.dto.BeaultyServiceDTO;
import com.giftflow.giftflow_backend.entities.BeaultyService;
import com.giftflow.giftflow_backend.repositories.BeaultyServiceRepository;
import java.sql.Timestamp;
import java.util.Date;

@Service
public class BeaultyServiceService {
    
    @Autowired
    private BeaultyServiceRepository serviceRepository;
    

    public BeaultyServiceDTO createService(BeaultyServiceDTO dto){
    
        BeaultyService service = new BeaultyService();
        service.setName(dto.getName());
        service.setPrice(dto.getPrice());
        service.setCreatedDate(new Timestamp((new Date()).getTime()));
        service.setUpdatedDate(new Timestamp((new Date()).getTime()));

        service = serviceRepository.save(service);
        dto.setId(service.getId());

        return dto;
    }

}
