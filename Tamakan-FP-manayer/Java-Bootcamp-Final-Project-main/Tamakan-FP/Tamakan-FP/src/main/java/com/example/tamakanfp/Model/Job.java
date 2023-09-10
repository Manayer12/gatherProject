package com.example.tamakanfp.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Job name must not be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String jobName;

    @NotEmpty(message = "Job description must not be empty")
    @Column(columnDefinition = "varchar(150) not null")
    private String jobDescription;

    @PositiveOrZero(message = "Working days must be a positive number")
    @NotNull(message = "Working days must not be empty")
    @Column(columnDefinition = "int not null")
    private Integer workingDays;

    @PositiveOrZero(message = "Working hours must be a positive number")
    @NotNull(message = "Working hours must not be empty")
    @Column(columnDefinition = "int UNSIGNED not null")
    private Integer workingHours;

    //@Pattern(regexp = "([0-9]{1,2})(-)([0-9]{1,2})(-)([0-9]{4})",message = "Start date must be a valid format")
    @NotNull(message = "Start date must not be empty")
    //date format
    @Column(columnDefinition = "DATE not null")
    private LocalDate startDate;

    //@Pattern(regexp = "([0-9]{1,2})(-)([0-9]{1,2})(-)([0-9]{4})",message = "End date must be a valid format")
    @NotNull(message = "End date must not be empty")
    //date format
    @Column(columnDefinition = "DATE not null")
    private LocalDate endDate;

    @NotEmpty(message = "City must not be empty")
    @Column(columnDefinition = "varchar(15) not null")
    private String city;

    @NotEmpty(message = "Address must not be empty")
    @Column(columnDefinition = "varchar(25) not null")
    private String address;

    @NotNull(message = "Salary must not be empty")
    @PositiveOrZero(message = "Salary must be a positive number")
    @Column(columnDefinition = "double UNSIGNED not null")
    private Double salary;

    @NotEmpty(message = "Sector must not be empty")
    @Column(columnDefinition = "varchar(15) not null")
    private String sector;


    @NotEmpty(message = "Status must not be empty")
    @Pattern(regexp = "unavailable|available", message = "Status must be available or unavailable")
    @Column(columnDefinition = "varchar(11) not null check(status REGEXP 'unavailable|available')")
    private String status;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "job")
    private Set<JobApplication> jobApplications;

    @ManyToOne
    @JoinColumn(name = "job_provider_id",referencedColumnName = "user_id")
    @JsonIgnore
    private JobProvider jobProvider;
}
