package com.example.tamakanfp.Service;


import com.example.tamakanfp.ApiResponse.ApiException;
import com.example.tamakanfp.DTO.RecommendationDTO;
import com.example.tamakanfp.Model.*;
import com.example.tamakanfp.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class RecommendationService {
    private final RecommendationRepositoory recommendationRepository;
    private final JobApplicationRepository jobApplicationRepository;
    private final JobSeekerRepository jobSeekerRepository;
    private final UserRepository userRepository;
    private final JobProviderRepository jobProviderRepository;

//provider get recommendations /seeker will see its oun recommendation in his profile
    public List<Recommendation> getAllRecommendByJobSeekerid(Integer user_id,Integer jobSeeker_id) {
        User user=userRepository.findUserById(user_id);
        if (user==null){
            throw new ApiException("user id is null");
        }

//        JobSeeker jobSeeker1=jobSeekerRepository.findJopSeekerById(user_id);
//        if (jobSeeker1==null){
//            throw new ApiException("id is null");
//        }

        JobSeeker jobSeeker = jobSeekerRepository.findJopSeekerById(jobSeeker_id);
        if (jobSeeker == null ) {
            throw new ApiException("id is null");
        }

        List<Recommendation>recommendations=recommendationRepository.getrecommendations(jobSeeker_id);

        return recommendations;
    }

//provider add recomendation
    public void addRecommendation(Integer user_id,Integer jobApp_id, RecommendationDTO recommendationDTO) {
        User user=userRepository.findUserById(user_id);
        if (user==null){
            throw new ApiException("user id is null");
        }

        JobProvider jobProvider1=jobProviderRepository.findJopProviderById(user_id);

        if (jobProvider1==null){
            throw new ApiException("id is null");
        }

        JobApplication jobsApp = jobApplicationRepository.findJobApplicationById(jobApp_id);
        if (jobsApp == null) {
            throw new ApiException("id is null");
        }

        LocalDate currentDate = LocalDate.now();
        if (!Objects.equals(jobsApp.getStatus(), "accepted") && !jobsApp.getJob().getEndDate().isBefore(currentDate)) {
            throw new ApiException("job application status is not accepted or the job period is not done yet! ");
        }

        Recommendation recommendation = new Recommendation(null, recommendationDTO.getReccomendations(), recommendationDTO.getCompany(), null);

        recommendation.setCompany(jobsApp.getJob().getJobProvider().getName());
        recommendation.setJobApplication(jobsApp);
        recommendationRepository.save(recommendation);

    }




}




