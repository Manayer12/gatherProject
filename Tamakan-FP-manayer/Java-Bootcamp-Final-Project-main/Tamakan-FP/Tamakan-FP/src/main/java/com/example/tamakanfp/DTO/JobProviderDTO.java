package com.example.tamakanfp.DTO;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class JobProviderDTO {


    private Integer User_id;


    @NotEmpty(message = "Username  is required")
    @Size(min=5,message = "The Username have to be more than 5 litters")
    @Column(unique = true)
    private String username;

    @NotEmpty(message = "Password is required")
    @Size(min=6,message = "The password have to be more than 5 Char")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z]).{6,}$" , message = "the password must have at least 1 number, 1 letter (upper or lower case) and min 6 char")
    private String password;

    @NotEmpty(message = "Email is required")
    @Email
    @Column(unique = true)
    private String email;


    private String role;


    @NotEmpty(message = "the company name is required")
    private String name;

    @NotEmpty(message = "Phone number is required")
    @Pattern(regexp = "(?:\\+?0*?966)?0?5[0-9]{8}")
    @Column(unique = true)
    private String phoneNumber;

    @Pattern(regexp = "^[A-Za-z\\s-']+$" , message = "Pleases enter a correct city name")
    @NotEmpty(message = "City is required")
    private String city;

    @NotEmpty(message = "Address is required")
    private String address;

    @NotEmpty(message = "Sector is required")
    private String sector;


    @NotEmpty(message = "license is required")
    private String license;

    @Pattern(regexp = "^(verify|pending)$" , message = "Pleases enter verify or pending")
    private String Status;

}
