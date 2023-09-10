package com.example.tamakanfp;

import com.example.tamakanfp.DTO.RecommendationDTO;
import com.example.tamakanfp.Model.*;
import com.example.tamakanfp.Repository.*;
import com.example.tamakanfp.Service.RecommendationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;


import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)

public class RecommendationServiceTest {
    @InjectMocks
    RecommendationService recommendationService;

    @Mock
    RecommendationRepositoory recommendationRepositoory;

    @Mock
    JobSeekerRepository jobSeekerRepository;

    @Mock
    UserRepository userRepository;


    Recommendation recommendation1;
    Recommendation recommendation2;


    JobProvider jobProvider1;

    JobSeeker jobSeeker1;



    JobApplication jobApplication1;

    User user;


    List<Recommendation> recommendationList;


    @BeforeEach
    void setUp() {

            jobApplication1=new JobApplication(1,"in-progress",null,jobSeeker1,null,recommendation1);
            jobSeeker1=new JobSeeker(null,"Manayer","966541594246","female","Riyadh","Street",23,user,null,null);
            jobProvider1=new JobProvider(null,"Manayer","966541594246","Riyadh","AulyaStreet","IT","12345","in-progress",user,null);
            user=new User(null,"Manayer2","Mm-232323","A@gmail.com","provider",jobSeeker1,jobProvider1);
            recommendation1=new Recommendation(null,"Good employee","Manayer",jobApplication1);
            recommendation2=new Recommendation(null,"HardWorker","SITE",jobApplication1);

        recommendationRepositoory.save(recommendation1);
            recommendationRepositoory.save(recommendation2);
            jobSeekerRepository.save(jobSeeker1);
            recommendationList=new ArrayList<>();
            recommendationList.add(recommendation1);
            recommendationList.add(recommendation2);
    }






@Test
    public void getAllRecommendation(){
        when(jobSeekerRepository.findJopSeekerById(jobSeeker1.getId())).thenReturn(jobSeeker1);
    when(userRepository.findUserById(user.getId())).thenReturn(user);
    when(recommendationRepositoory.getrecommendations(jobSeeker1.getId())).thenReturn(recommendationList);
        List<Recommendation>recommendations=recommendationService.getAllRecommendByJobSeekerid(user.getId(), jobSeeker1.getId());
        Assertions.assertEquals(recommendations,recommendationList);
        verify(jobSeekerRepository,times(1)).findJopSeekerById(jobSeeker1.getId());
        verify(recommendationRepositoory,times(1)).getrecommendations(jobSeeker1.getId());
        verify(userRepository,times(1)).findUserById(user.getId());

}



}
