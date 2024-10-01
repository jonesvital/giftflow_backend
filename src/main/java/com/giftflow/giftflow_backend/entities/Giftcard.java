package com.giftflow.giftflow_backend.entities;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
    @Temporal(TemporalType.DATE)
    private Date purchaseDate;

    @Column(name = "service_date")
    @Temporal(TemporalType.DATE)
    private Date serviceDate;

    @Column(name = "giftcard_uuid")
    private UUID giftcardUuid;

    @Column(name = "s3_uri")
    private String s3Uri;
}
