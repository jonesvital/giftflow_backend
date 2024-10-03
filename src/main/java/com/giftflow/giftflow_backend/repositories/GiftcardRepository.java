package com.giftflow.giftflow_backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.giftflow.giftflow_backend.entities.Giftcard;

@Repository
public interface GiftcardRepository extends JpaRepository<Giftcard, Integer>{

    @Query(
        value = """
                select g.* from giftcards g where g.giftcard_uuid = :uuid 
                """,
        nativeQuery = true
    )
    public Giftcard getGiftcardByUUID(UUID uuid);
    
}
