package com.giftflow.giftflow_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GiftCardDTO {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("from_person_name")
    private String fromPersonName;

    @JsonProperty("from_person_phone")
    private String fromPersonPhone;

    @JsonProperty("to_person_name")
    private String toPersonName;

    @JsonProperty("to_person_phone")
    private String toPersonPhone;

    @JsonProperty("service_name")
    private String serviceName;

    @JsonProperty("service_id")
    private Integer serviceId;

    @JsonProperty("purchase_date")
    private String purchaseDate;

    @JsonProperty("service_date")
    private String serviceDate;
    
}
