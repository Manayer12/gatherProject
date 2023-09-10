package com.example.tamakanfp.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;


@Data
@AllArgsConstructor
@Entity
@RequiredArgsConstructor
public class JobProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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



    @OneToOne
    @MapsId
    @JsonIgnore
    User user;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "jobProvider")
    private Set<Job> jobs;


}
