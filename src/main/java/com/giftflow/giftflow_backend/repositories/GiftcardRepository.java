package com.giftflow.giftflow_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giftflow.giftflow_backend.entities.Giftcard;

@Repository
interface GiftcardRepository extends JpaRepository<Giftcard, Integer>{
    
}
