package com.example.tamakanfp.Repository;

import com.example.tamakanfp.Model.Certificates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Repository
public interface CertificatesRepository extends JpaRepository<Certificates, File> {
    Optional<Certificates> findByName(String fileName);


    List<Certificates> findCertificatesByJobApplication_JobSeekerId(Integer id);




}


