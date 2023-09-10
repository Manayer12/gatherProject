package com.example.tamakanfp.Repository;

import com.example.tamakanfp.Model.Job;
import com.example.tamakanfp.Model.JobApplication;
import com.example.tamakanfp.Model.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication,Integer> {


    JobApplication findJobApplicationById(Integer id);
    List<JobApplication> findJobApplicationByJobSeeker(JobSeeker jobSeeker);

    JobApplication findJobApplicationByJobSeekerAndJob(JobSeeker jobSeeker, Job job);

    JobApplication findJobApplicationByIdAndJobSeeker(Integer jobApplicationId,JobSeeker jobSeeker);

    @Query("select j from JobApplication j where j.id=?1 and j.job.jobProvider.id=?2")
    JobApplication getJobApplicationIdAndByJobProviderId(Integer jobApplicationId,Integer providerId);

    List<JobApplication> findJobApplicationByJobSeekerAndStatus(JobSeeker jobSeeker, String status);


    ////

    List<JobApplication> findJobApplicationByJob(Job job);

    List<JobApplication> findJobApplicationByJobSeeker_JobSeekerProfile_University(String University);

    List<JobApplication> findJobApplicationByJobSeeker_JobSeekerProfile_MajorAndJob_Id(String Major , Integer jobId);

    List<JobApplication> findJobApplicationByJobSeeker_JobSeekerProfileGPAAndJob_Id(Double GPA , Integer jobId) ;


    @Query("select j from JobApplication j where j.job.id=?1 ORDER BY j.jobSeeker.jobSeekerProfile.GPA DESC ")
    List<JobApplication> findJobApplicationByJob_Id(Integer JobId);


    List<JobApplication> findJobApplicationByStatusAndJob_Id(String Status, Integer jobId);



    List<JobApplication> findJobApplicationByStatusAndJobSeeker_Id(String Status, Integer jobId);

}
