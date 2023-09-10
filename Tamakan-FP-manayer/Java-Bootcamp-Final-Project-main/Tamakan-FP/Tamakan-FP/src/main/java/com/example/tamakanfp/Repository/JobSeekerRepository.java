package com.example.tamakanfp.Repository;


import com.example.tamakanfp.Model.JobApplication;
import com.example.tamakanfp.Model.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeeker,Integer> {

    JobSeeker findJopSeekerById(Integer id);

}
