package com.example.tamakanfp.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JobSeekerProfileDTO {



    @NotEmpty(message = "Out of GPA must not be empty")
    @Pattern(regexp = "4|5",message = "Out of GPA must be 4 or 5")
    @Column(columnDefinition = "varchar(1) not null check(out0fGPA REGEXP '4|5')")
    private String out0fGPA;

    @ElementCollection
    @NotNull(message = "Courses must not be empty")
    private List<String> courses;

    @ElementCollection
    @NotNull(message = "Skills must not be empty")
    private List<String> skills;


    @PositiveOrZero(message = "GPA must be a positive number")
    @NotNull(message = "GPA must not be empty")
    @Column(columnDefinition = "double UNSIGNED not null")
    private Double GPA;


    @NotEmpty(message = "Major must not be empty")
    @Column(columnDefinition = "varchar(15) not null")
    private String major;

    @NotEmpty(message = "University must not be empty")
    @Column(columnDefinition = "varchar(25) not null")
    private String university;

    @Pattern(regexp = "bachelor|master|diploma",message = "Academic qualification must be bachelor or master or diploma ")
    @NotEmpty(message = "Academic qualification must not be empty")
    @Column(columnDefinition = "varchar(20) not null check(academic_qualification REGEXP 'bachelor|master|diploma')")
    private String academicQualification;

    @Pattern(regexp = "[0-9]{4}",message = "Expected graduation year must be a valid format")
    @NotEmpty(message = "Expected graduation year must not be empty")
    //date format
    @Column(columnDefinition = "varchar(4) not null check(expected_graduation_year REGEXP '[0-9]{4}')")
    private String expectedGraduationYear;


}
