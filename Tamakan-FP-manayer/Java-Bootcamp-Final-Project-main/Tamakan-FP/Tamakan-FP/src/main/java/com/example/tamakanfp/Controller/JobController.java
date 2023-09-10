package com.example.tamakanfp.Controller;


import com.example.tamakanfp.ApiResponse.ApiResponse;
import com.example.tamakanfp.Model.Job;
import com.example.tamakanfp.Model.User;
import com.example.tamakanfp.Service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/job")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping("get-jobs")
    public ResponseEntity getJobs() {
        return ResponseEntity.status(200).body(jobService.getJobs());
    }

    @PostMapping("add-job/{jobProviderId}")
    public ResponseEntity addJob(@RequestBody @Valid Job job,@PathVariable Integer jobProviderId) {
        jobService.addJob(job,jobProviderId);
        return ResponseEntity.status(200).body(new ApiResponse("Job added"));
    }

    @PutMapping("update-job/{id}/{jobProviderId}")
    public ResponseEntity updateJob(@RequestBody @Valid Job job,@PathVariable Integer id,@PathVariable Integer jobProviderId) {
        jobService.updateJob(job,id,jobProviderId);
        return ResponseEntity.status(200).body(new ApiResponse("Job updated"));

    }
    @DeleteMapping("delete-job/{id}/{jobProviderId}")
    public ResponseEntity deleteJob(@PathVariable Integer id,@PathVariable Integer jobProviderId) {
        jobService.deleteJob(id,jobProviderId);
        return ResponseEntity.status(200).body(new ApiResponse("Job deleted"));

    }

    @GetMapping("get-job-by-job-provider-id/{id}")
    public ResponseEntity getJobByJobProviderId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(jobService.getJobByJobProviderId(id));

    }
    @GetMapping("get-job-by-job-status/{status}")
    public ResponseEntity getJobByJobStatus(@PathVariable String status){
        return ResponseEntity.status(200).body(jobService.getJobByJobStatus(status));
    }
    @GetMapping("get-job-by-job-sector/{sector}")
    public ResponseEntity getJobByJobSector(@PathVariable String sector){
        return ResponseEntity.status(200).body(jobService.getJobByJobSector(sector));
    }

    @PutMapping("update-job-status/{id}")
    public ResponseEntity updateJobByJobStatus(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(new ApiResponse(jobService.updateJobStatus(id)));

    }

    @PutMapping("stop-receiving-applications/{id}/{jobProviderId}")
    public ResponseEntity stopReceivingApplications(@PathVariable Integer id,@PathVariable Integer jobProviderId){
       jobService.stopReceivingApplications(id,jobProviderId);
        return ResponseEntity.status(200).body(new ApiResponse("Job status updated"));

    }


    @GetMapping("/get-by-salary/{salary}")
    private ResponseEntity getJobBySalary( @PathVariable Double salary){
        return ResponseEntity.status(200).body(jobService.getJobBySalary(salary));
    }

    @GetMapping("/get-by-id/{job_id}")
    private ResponseEntity getJobBySalary(@PathVariable Integer job_id){
        return ResponseEntity.status(200).body(jobService.getJobById(job_id));
    }

    @GetMapping("/get-by-name/{jobName}")
    private ResponseEntity getJobBySalary(@PathVariable String jobName){
        return ResponseEntity.status(200).body(jobService.getJobByName(jobName));
    }

    @GetMapping("/get-by-city/{city}")
    private ResponseEntity getJobByCity(@PathVariable String city){
        return ResponseEntity.status(200).body(jobService.getJobByCity(city));
    }

    @GetMapping("/get-by-endDate/{endDate}")
    private ResponseEntity getJobByCity(@PathVariable LocalDate endDate){
        return ResponseEntity.status(200).body(jobService.getJobByEndDate(endDate));
    }

    @GetMapping("/count-all-jobs")
    private ResponseEntity countAllJobs(){
        return ResponseEntity.status(200).body(jobService.countjobs());
    }


    @GetMapping("/count-job-salary/{salary}")
    private ResponseEntity countjobbySalary(Double salary){
        return ResponseEntity.status(200).body(jobService.countJobBySalary(salary));
    }
    @GetMapping("/count-job-name/{name}")
    private ResponseEntity countjobbyName(String name){
        return ResponseEntity.status(200).body(jobService.jobbynamecount(name));
    }
    @GetMapping("/count-job-city/{city}")
    private ResponseEntity countjobbycity(String city){
        return ResponseEntity.status(200).body(jobService.countjobscity(city));
    }

    @GetMapping("/count-job-enddate/{enddate}")
    private ResponseEntity countjobbyEndDate(LocalDate enddate){
        return ResponseEntity.status(200).body(jobService.countjobsEndDate(enddate));
    }


}