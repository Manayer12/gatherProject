package com.example.tamakanfp.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Certificates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(30)")
    private String name;
    @Column(columnDefinition = "varchar(30)")
    private String type;
    @Lob
    @Column(name = "fileData",length = 1048576)
    private byte[] fileData;

    @OneToOne
    @MapsId
    @JsonIgnore
    JobApplication jobApplication;



}
