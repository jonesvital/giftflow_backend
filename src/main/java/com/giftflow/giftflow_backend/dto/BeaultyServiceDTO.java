package com.giftflow.giftflow_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BeaultyServiceDTO {

    @JsonProperty("service_id")
    private Integer id;

    @JsonProperty("service_name")
    private String name;

    @JsonProperty("price")
    private Double price;
}
