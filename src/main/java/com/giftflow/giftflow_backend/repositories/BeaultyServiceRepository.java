package com.giftflow.giftflow_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giftflow.giftflow_backend.entities.BeaultyService;

@Repository
public interface BeaultyServiceRepository extends JpaRepository<BeaultyService, Integer> {
    
}
