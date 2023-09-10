package com.example.tamakanfp.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(255) ")
    private  String reccomendation;

    @Column(columnDefinition = "varchar(25) ")
    private String Company;



    @OneToOne
    @MapsId
    @JsonIgnore
    JobApplication jobApplication;


}
