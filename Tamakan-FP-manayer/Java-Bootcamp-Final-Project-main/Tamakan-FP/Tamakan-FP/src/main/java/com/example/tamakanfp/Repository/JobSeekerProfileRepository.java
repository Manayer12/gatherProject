package com.example.tamakanfp.Repository;

import com.example.tamakanfp.Model.JobSeeker;
import com.example.tamakanfp.Model.JobSeekerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobSeekerProfileRepository extends JpaRepository<JobSeekerProfile,Integer> {

    JobSeekerProfile findJobSeekerProfileById(Integer id);
    JobSeekerProfile findJobSeekerProfileByIdAndJobSeeker(Integer id,JobSeeker jobSeeker);

    Optional<JobSeekerProfile> findJobSeekerProfileByJobSeeker(JobSeeker jobSeeker); //to download file by its name. Optional type used to return value(empty and a non-null), for might not always have a result to return
}
