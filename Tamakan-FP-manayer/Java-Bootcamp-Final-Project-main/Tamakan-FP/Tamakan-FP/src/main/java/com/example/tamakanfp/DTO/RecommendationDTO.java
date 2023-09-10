package com.example.tamakanfp.DTO;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Valid
public class RecommendationDTO {


    private Integer jobApp_id;

    private  String reccomendations;

    private String Company;
}