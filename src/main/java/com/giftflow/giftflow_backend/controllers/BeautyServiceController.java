package com.giftflow.giftflow_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giftflow.giftflow_backend.dto.BeaultyServiceDTO;
import com.giftflow.giftflow_backend.services.BeaultyServiceService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/beauty-services")
public class BeautyServiceController {

    @Autowired
    private BeaultyServiceService beautyServiceService;
    

    @PostMapping()    
    public BeaultyServiceDTO createService(@RequestBody BeaultyServiceDTO dto) {
        return beautyServiceService.createService(dto);
    }

    @GetMapping()
    public List<BeaultyServiceDTO> listServices() {
        return beautyServiceService.listServices();
    }

    @PutMapping()
    public BeaultyServiceDTO updateService(@RequestBody BeaultyServiceDTO dto) {
        return beautyServiceService.updateService(dto);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteService(@PathVariable("id") Integer id) {
        beautyServiceService.deleteService(id);

        return ResponseEntity.ok().build();
    }
    
    
}
