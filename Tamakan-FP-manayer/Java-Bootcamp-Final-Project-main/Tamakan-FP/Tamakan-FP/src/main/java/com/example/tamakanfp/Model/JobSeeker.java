package com.example.tamakanfp.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@Entity
@RequiredArgsConstructor
public class JobSeeker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "Your full name is required")
    @Pattern(regexp = "^[A-Za-z]+ [A-Za-z]+$" , message = "Please enter your first and last name")
    private String name;

    @NotEmpty(message = "Phone number is required")
    @Pattern(regexp = "(?:\\+?0*?966)?0?5[0-9]{8}")
    @Column(unique = true)
    private String phoneNumber;

    @Pattern(regexp = "^(female|male)$" , message = "Pleases enter male or female")
    @NotEmpty(message = "Gender is required")
    private String gender;



    @Pattern(regexp = "^[A-Za-z\\s-']+$" , message = "Pleases enter a correct city name")
    @NotEmpty(message = "City is required")
    private String city;

    @NotEmpty(message = "Address is required")
    private String address;

    @Min(value = 18 ,message = "must be above 18 to be a university  or college student")
    @NotNull(message = "Age is required")
    private Integer age;



    @OneToOne
    @MapsId
    @JsonIgnore
    User user;

    @OneToOne(cascade =CascadeType.ALL,mappedBy = "jobSeeker")
    @PrimaryKeyJoinColumn
    JobSeekerProfile jobSeekerProfile;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "jobSeeker")
    private Set<JobApplication> jobApplications;



}
