package com.example.tamakanfp.Service;

import com.example.tamakanfp.ApiResponse.ApiException;
import com.example.tamakanfp.Model.Job;
import com.example.tamakanfp.Model.JobApplication;
import com.example.tamakanfp.Model.JobProvider;
import com.example.tamakanfp.Model.JobSeeker;
import com.example.tamakanfp.Repository.JobApplicationRepository;
import com.example.tamakanfp.Repository.JobProviderRepository;
import com.example.tamakanfp.Repository.JobRepository;
import com.example.tamakanfp.Repository.JobSeekerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final JobRepository jobRepository;
    private final JobSeekerRepository jobSeekerRepository;

    public List<JobApplication> getJobApplications(){
        return jobApplicationRepository.findAll();
    }

    public void  addJobApplication(Integer jobId,Integer seekerId){

        Job job=jobRepository.findJobById(jobId);
        JobSeeker seeker=jobSeekerRepository.findJopSeekerById(seekerId);
        JobApplication application=jobApplicationRepository.findJobApplicationByJobSeekerAndJob(seeker,job);
        if (job==null ||seeker==null)
            throw new ApiException("Job id or seeker id not found");

        if (application!=null || seeker.getJobSeekerProfile() ==null)
            throw new ApiException("Can't apply the job");

        JobApplication jobApplication=new JobApplication(null,"in-progress",job,seeker,null,null);
        jobApplicationRepository.save(jobApplication);
    }

    public String updateJobApplicationStatus(Integer id){

        JobApplication jobApplication=jobApplicationRepository.findJobApplicationById(id);
        if (jobApplication==null)
            throw new ApiException("Job application id not found");

        if (jobApplication.getJob().getStatus().equals("unavailable") && (!jobApplication.getStatus().equals("accepted") || !jobApplication.getStatus().equals("rejected")))
        {jobApplication.setStatus("rejected");
        jobApplicationRepository.save(jobApplication);
        return "Job application status updated";}

        return null;
    }

    public void rejectJobApplicationByJobProvider(Integer jobProviderId,Integer jobApplicationId){
        JobApplication application=jobApplicationRepository.getJobApplicationIdAndByJobProviderId(jobApplicationId,jobProviderId);
        if (application==null)
            throw new ApiException("Job application not found");

        if (application.getStatus().equals("rejected")|| application.getJob().getStatus().equals("unavailable"))
            throw new ApiException("Can't change a application status to rejected");

        //if accepted or in-in-progress or hiring-process
        application.setStatus("rejected");
        jobApplicationRepository.save(application);
    }
    public void rejectJobApplicationByJobSeeker(Integer jobSeekerId,Integer jobApplicationId){
        JobSeeker seeker=jobSeekerRepository.findJopSeekerById(jobSeekerId);
        JobApplication application=jobApplicationRepository.findJobApplicationByIdAndJobSeeker(jobApplicationId,seeker);

        if (application==null || seeker ==null)
            throw new ApiException("Job application or job seeker not found");
        if (application.getStatus().equals("rejected") || application.getStatus().equals("accepted")||application.getStatus().equals("hiring-process")|| application.getJob().getStatus().equals("unavailable"))
            throw new ApiException("Can't rejected of the job");

        //if in-progress

        application.setStatus("rejected");
        jobApplicationRepository.save(application);
    }

    public void processJobApplication(Integer jobProviderId,Integer jobApplicationId){
        JobApplication application=jobApplicationRepository.getJobApplicationIdAndByJobProviderId(jobApplicationId,jobProviderId);
        if (application==null)
            throw new ApiException("Job application not found");

        if (application.getStatus().equals("accepted")||application.getStatus().equals("hiring-process")||application.getStatus().equals("rejected") ||application.getJob().getStatus().equals("unavailable"))
            throw new ApiException("Can't change a application status to hiring-process");

        //if in-progress or rejected
        application.setStatus("hiring-process");
        jobApplicationRepository.save(application);

    }

    public List<JobApplication> GetJobApplicationsByJobSeekerId(Integer seekerId){
        JobSeeker seeker=jobSeekerRepository.findJopSeekerById(seekerId);
        List<JobApplication> jobApplications=jobApplicationRepository.findJobApplicationByJobSeeker(seeker);

        if (seeker==null || jobApplications== null)
            throw new ApiException("Job seeker or job applications not found");


        return  jobApplications;
    }
    public Integer countJobApplicationsByStatusAndSeekerId(String status,Integer seekerId){
        JobSeeker seeker=jobSeekerRepository.findJopSeekerById(seekerId);
        List<JobApplication> jobApplications=jobApplicationRepository.findJobApplicationByJobSeekerAndStatus(seeker,status);

        if (seeker==null || jobApplications== null)
            throw new ApiException("Job seeker or job applications not found");


        return  jobApplications.size();
    }

    //1-8

    public List<JobApplication> getApplicationByJobID(Integer job_Id) {
        Job job = jobRepository.findJobById(job_Id);
        List<JobApplication> jobApplications = jobApplicationRepository.findJobApplicationByJob(job);
        if(jobApplications.isEmpty()){
            throw new ApiException("No job application yet");
        }
        return jobApplications;
    }


    public List<JobApplication> getApplicationbyUni(String uni) {
        List<JobApplication> jobApplications = jobApplicationRepository.findJobApplicationByJobSeeker_JobSeekerProfile_University(uni);
        if(jobApplications.isEmpty()){
            throw new ApiException("There are no applicants from this university ");
        }
        return jobApplications;
    }


    public List<JobApplication> findJobApplicationByMajor(String major, Integer jobId) {
        List<JobApplication> jobApplications = jobApplicationRepository.findJobApplicationByJobSeeker_JobSeekerProfile_MajorAndJob_Id(major, jobId);
        if(jobApplications.isEmpty()){
            throw new ApiException("There are no applicants from this major ");
        }
        return jobApplications;
    }


    public List<JobApplication> findJobApplicationByGPA(Double gpa, Integer jobId) {
        List<JobApplication> jobApplications = jobApplicationRepository.findJobApplicationByJobSeeker_JobSeekerProfileGPAAndJob_Id(gpa, jobId);
        if(jobApplications.isEmpty()){
            throw new ApiException("There are no applicants with this GPA");
        }
        return jobApplications;
    }


    public List<JobApplication> sortJobApplicationByGPA(Integer jobId) {
        List<JobApplication> jobApplications = jobApplicationRepository.findJobApplicationByJob_Id(jobId);
        if (jobApplications.isEmpty()) {
            throw new ApiException("No job Application");
        }
        return jobApplications;
    }


    public List<JobApplication> findJobApplicationByStatusandJobid(String Status, Integer jobId) {
        List<JobApplication> jobApplications = jobApplicationRepository.findJobApplicationByStatusAndJob_Id(Status, jobId);
        if(jobApplications.isEmpty()){
            throw new ApiException("There are no applicants with this Status");
        }
        return jobApplications;
    }


    public List<JobApplication> findJobApplicationByStatusandJobSeekerid(String Status, Integer jobSeekerid) {
        List<JobApplication> jobApplications = jobApplicationRepository.findJobApplicationByStatusAndJobSeeker_Id(Status, jobSeekerid);
        if(jobApplications.isEmpty()){
            throw new ApiException("You do not have job application yet");
        }
        return jobApplications;
    }



    public void ChangeStatustoAccept (Integer jobApplication_Id){
        JobApplication jobApplication = jobApplicationRepository.findJobApplicationById(jobApplication_Id);
        if(jobApplication == null){
            throw new ApiException("There is No Job Application");
        }
        jobApplication.setStatus("accepted");
        jobApplicationRepository.save(jobApplication);
    }



}
