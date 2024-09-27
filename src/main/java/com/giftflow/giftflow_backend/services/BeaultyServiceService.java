package com.giftflow.giftflow_backend.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giftflow.giftflow_backend.dto.BeaultyServiceDTO;
import com.giftflow.giftflow_backend.entities.BeaultyService;
import com.giftflow.giftflow_backend.repositories.BeaultyServiceRepository;

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

    public List<BeaultyServiceDTO> listServices(){
         return serviceRepository.findAll().stream().map((s) -> new BeaultyServiceDTO(s.getId(),s.getName(), s.getPrice())).toList();
    }

    public BeaultyServiceDTO updateService(BeaultyServiceDTO dto){
        BeaultyService service = serviceRepository.findById(dto.getId()).orElse(null);
        if(service != null){
            service.setName(dto.getName());
            service.setPrice(dto.getPrice());
            service.setUpdatedDate(new Timestamp((new Date()).getTime()));
            serviceRepository.save(service);
        }
        return dto;
    }

    public void deleteService(Integer id){
        serviceRepository.deleteById(id);
    }
}
