package com.example.tamakanfp.Repository;

import com.example.tamakanfp.Model.Job;
import com.example.tamakanfp.Model.JobProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,Integer> {

    Job findJobById(Integer id);
    Job findJobByIdAndJobProvider(Integer id, JobProvider jobProvider);

    List<Job> findJobByJobProvider(JobProvider jobProvider);
    List<Job> findJobByStatus(String status);
    List<Job> findJobBySector(String status);

    Job findTopByOrderByIdDesc();

    List<Job> findJobBySalary(Double salary);
    List<Job> findJobByJobName(String jobName);

    List<Job> findJobByCity(String city);

    List<Job> findJobByEndDate(LocalDate endDate);


    long count();

}
