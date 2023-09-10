package com.example.tamakanfp;

import com.example.tamakanfp.Model.Job;
import com.example.tamakanfp.Model.JobProvider;
import com.example.tamakanfp.Repository.JobProviderRepository;
import com.example.tamakanfp.Repository.JobRepository;
import com.example.tamakanfp.Service.JobService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class JobServiceTest {


    @InjectMocks
    JobService jobService;

    @Mock
    JobRepository jobRepository;

    @Mock
    JobProviderRepository jobProviderRepository;



    Job job1;
    Job job2;


    JobProvider jobProvider;

    @BeforeEach
    void setUp() {

        job1=new Job(null,"developer","development",60,120,null,null,"Riyadh","aulya",1000.0,"IT",null,null,null)

        jobProvider=new JobProvider(null,"SITE","0541594246","jeddah","aulya","it","12345","in progress",null,null);



    }
}
