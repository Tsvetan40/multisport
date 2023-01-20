package com.demo.multisport.dto.page;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RatingDto {

    @NonNull
    @JsonProperty(required = true)
    private Double rate;

    @NonNull
    @JsonProperty(required = true)
    private String address;
}
