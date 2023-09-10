package com.example.tamakanfp.Controller;


import com.example.tamakanfp.ApiResponse.ApiResponse;
import com.example.tamakanfp.Model.JobApplication;
import com.example.tamakanfp.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/job-application")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;


    @GetMapping("get-job-applications")
    private ResponseEntity getJobApplications() {
        return ResponseEntity.status(200).body(jobApplicationService.getJobApplications());
    }

    @PostMapping("add-job-application/{jobId}/{seekerId}")
    private ResponseEntity addJobApplication(@PathVariable Integer jobId,@PathVariable Integer seekerId) {
        jobApplicationService.addJobApplication(jobId,seekerId);
        return ResponseEntity.status(200).body(new ApiResponse("Job Application added"));
    }
    @PutMapping("update-job-application-status/{id}")
    private ResponseEntity updateJobApplication(@PathVariable Integer id) {

        return ResponseEntity.status(200).body(new ApiResponse(jobApplicationService.updateJobApplicationStatus(id)));

    }

    @PutMapping("reject-job-application-by-job-provider/{providerId}/{id}")
    private ResponseEntity rejectJobApplicationByJobProvider(@PathVariable Integer providerId, @PathVariable Integer id) {
        jobApplicationService.rejectJobApplicationByJobProvider(providerId, id);
        return ResponseEntity.status(200).body(new ApiResponse("Job Application status updated"));

    }
    @PutMapping("reject-job-application-by-job-seeker/{seekerId}/{id}")
    private ResponseEntity rejectJobApplicationByJobSeeker(@PathVariable Integer seekerId, @PathVariable Integer id) {
        jobApplicationService.rejectJobApplicationByJobSeeker(seekerId, id);
        return ResponseEntity.status(200).body(new ApiResponse("Job Application status updated"));

    }

    @PutMapping("process-job-application/{providerId}/{id}")
    private ResponseEntity processJobApplication(@PathVariable Integer providerId, @PathVariable Integer id) {
        jobApplicationService.processJobApplication(providerId, id);
        return ResponseEntity.status(200).body(new ApiResponse("Job Application status updated"));

    }

    @GetMapping("get-job-applications-by-job-seeker-id/{id}")
    public ResponseEntity GetJobApplicationsByJobSeekerId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(jobApplicationService.GetJobApplicationsByJobSeekerId(id));

    }
    @GetMapping("count-job-applications-by-status-and-seeker-id/{status}/{id}")
    public ResponseEntity countJobApplicationsByStatusAndSeekerId(@PathVariable String status,@PathVariable Integer id){
        return ResponseEntity.status(200).body(jobApplicationService.countJobApplicationsByStatusAndSeekerId(status,id));

    }

    /////

    @GetMapping("getApplicationByJobID/{job_id}")
    private ResponseEntity getApplicationByJobID(@PathVariable Integer job_id){
        return ResponseEntity.status(200).body(jobApplicationService.getApplicationByJobID(job_id));
    }

    @GetMapping("getApplicationbyUni/{uni}")
    private ResponseEntity getApplicationbyUni(@PathVariable String uni){
        return ResponseEntity.status(200).body(jobApplicationService.getApplicationbyUni(uni));
    }



    @GetMapping("getApplicationbymajor/{jobId}/{major}")
    private ResponseEntity getApplicationbyMajor(@PathVariable String major , @PathVariable Integer jobId){
        return ResponseEntity.status(200).body(jobApplicationService.findJobApplicationByMajor(major,jobId));
    }


    @GetMapping("getApplicationbygpa/{gpa}/{jobId}")
    private ResponseEntity getApplicationbyGPA(@PathVariable Double gpa , @PathVariable Integer jobId){
        return ResponseEntity.status(200).body(jobApplicationService.findJobApplicationByGPA(gpa,jobId));
    }


    @GetMapping("sortJobApplicationByGPA/{jobId}")
    private ResponseEntity sortJobApplicationByGPA(@PathVariable Integer jobId){
        return ResponseEntity.status(200).body(jobApplicationService.sortJobApplicationByGPA(jobId));
    }


    @GetMapping("getApplicationbystatusandjobid/{jobId}/{Status}")
    private ResponseEntity getApplicationbyStatus(@PathVariable String Status , @PathVariable Integer jobId){
        return ResponseEntity.status(200).body(jobApplicationService.findJobApplicationByStatusandJobid(Status,jobId));
    }

    @GetMapping("getApplicationbystatusandseekerid/{jobseekerId}/{Status}")
    private ResponseEntity getApplicationbyStatusandseeker(@PathVariable String Status , @PathVariable Integer jobseekerId){
        return ResponseEntity.status(200).body(jobApplicationService.findJobApplicationByStatusandJobSeekerid(Status,jobseekerId));
    }



    @GetMapping("changeStatustoAccept/{Id}")
    private ResponseEntity ChangeStatustoAccept(@PathVariable Integer Id){
        jobApplicationService.ChangeStatustoAccept(Id);
        return ResponseEntity.status(200).body(new ApiResponse("Job Application status changed to accept"));
    }
}
