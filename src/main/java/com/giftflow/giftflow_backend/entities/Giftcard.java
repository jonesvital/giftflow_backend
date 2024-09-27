package com.giftflow.giftflow_backend.entities;

import java.sql.Timestamp;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "giftcards")
public class Giftcard {

    @Id
    @Column(name = "giftcard_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "service_id_service")
    private BeaultyService service;
    
    @Column(name = "from_person")
    private String fromPerson;

    @Column(name = "from_person_phone")
    private String fromPersonPhone;
    
    @Column(name = "to_person")
    private String toPerson;

    @Column(name = "to_person_phone")
    private String toPersonPhone;

    @Column(name = "purchase_date")
    private Timestamp purchaseDate;

    @Column(name = "service_date")
    private Timestamp serviceDate;

    @Column(name = "giftcard_uuid")
    private UUID giftcardUuid;

    @Column(name = "s3_uri")
    private String s3Uri;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "updated_date")
    private Timestamp updatedDate;
}
