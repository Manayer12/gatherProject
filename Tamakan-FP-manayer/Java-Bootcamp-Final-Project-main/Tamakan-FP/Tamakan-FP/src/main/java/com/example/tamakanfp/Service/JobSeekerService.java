package com.example.tamakanfp.Service;


import com.example.tamakanfp.ApiResponse.ApiException;
import com.example.tamakanfp.DTO.JobSeekerDTO;
import com.example.tamakanfp.Model.Job;
import com.example.tamakanfp.Model.JobSeeker;
import com.example.tamakanfp.Model.JobSeekerProfile;
import com.example.tamakanfp.Model.User;
import com.example.tamakanfp.Repository.JobRepository;
import com.example.tamakanfp.Repository.JobSeekerProfileRepository;
import com.example.tamakanfp.Repository.JobSeekerRepository;
import com.example.tamakanfp.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobSeekerService {


    private final JobSeekerRepository jobSeekerRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final JobSeekerProfileRepository jobSeekerProfileRepository;



    public void regester(JobSeekerDTO jobSeekerDTO)
    {

        String hash = new BCryptPasswordEncoder().encode(jobSeekerDTO.getPassword());


        User user1 = new User(null,jobSeekerDTO.getUsername(), hash, jobSeekerDTO.getEmail(),"seeker",null,null);
        JobSeeker jobSeeker=new JobSeeker(null,jobSeekerDTO.getName(),jobSeekerDTO.getPhoneNumber(),jobSeekerDTO.getGender(),jobSeekerDTO.getCity(),jobSeekerDTO.getAddress(),jobSeekerDTO.getAge(),user1,null,null);
        user1.setJopSeeker(jobSeeker);

        jobSeekerRepository.save(jobSeeker);
        userRepository.save(user1);
    }

    public  void  updateJobSeeker (Integer user_id , Integer id , JobSeekerDTO jobSeekerDTO){
        User user = userRepository.findUserById(user_id);
        JobSeeker oldJobSeeker= jobSeekerRepository.findJopSeekerById(id);
        if (user==null){
            throw new ApiException("user not found");
        }
        if (oldJobSeeker == null) {
            throw new ApiException("job Provider is null");
        }

        if (oldJobSeeker.getUser().getId() != user_id){
            throw new ApiException("user is not allowed");
        }

        user.setUsername(jobSeekerDTO.getUsername());
        user.setPassword(jobSeekerDTO.getPassword());
        user.setEmail(jobSeekerDTO.getEmail());
        user.setRole(jobSeekerDTO.getRole());
        oldJobSeeker.setUser(user);
        oldJobSeeker.setName(jobSeekerDTO.getName());
        oldJobSeeker.setPhoneNumber(jobSeekerDTO.getPhoneNumber());
        oldJobSeeker.setGender(jobSeekerDTO.getGender());
        oldJobSeeker.setCity(jobSeekerDTO.getCity());
        oldJobSeeker.setAddress(jobSeekerDTO.getAddress());
        oldJobSeeker.setAge(jobSeekerDTO.getAge());

        jobSeekerRepository.save(oldJobSeeker);

    }


    private  final JavaMailSender javaMailSender;
    //the update
    public  String sendJobOffer() { //call in job service

        Job job = jobRepository.findTopByOrderByIdDesc();
        List<JobSeekerProfile> jobSeekerProfileS = jobSeekerProfileRepository.findAll();
        for (JobSeekerProfile js : jobSeekerProfileS) {
            if (js.getMajor().equals(job.getSector())) {

                SimpleMailMessage message = new SimpleMailMessage();

                String sendto = js.getJobSeeker().getUser().getEmail();

                message.setFrom("mmm555op@gmail.com");
                message.setTo(sendto);
                message.setSubject("There is a new suitable Part-Time Job  for you in Tamakan");
                message.setText("There is a new suitable Part-Time Job for you in Tamakan \n Job Name:" + job.getJobName() + "\n Job Description: " + job.getJobDescription());

                javaMailSender.send(message);


                return "Your Mail sent successfully";

            }

            return "No match";

        }

        return "end";

    }




//    private final CustomerRepository customerRepository;
//    private final UserRepository userRepository;
//
//    public void regester(CustomerDTO customerDTO)
//    {
//
//        String hash = new BCryptPasswordEncoder().encode(customerDTO.getPassword());
//
//
//        User user1 = new User(null,customerDTO.getUsername(), hash, "Customer",null,null);
//        Customer customer =new Customer(null,user1,null,null);
//        user1.setCustomer(customer);
//
//        customerRepository.save(customer);
//        userRepository.save(user1);
//    }





}
