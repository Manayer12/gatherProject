package com.example.tamakanfp.Repository;

import com.example.tamakanfp.Model.JobApplication;
import com.example.tamakanfp.Model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationRepositoory extends JpaRepository<Recommendation,Integer> {

    Recommendation findRecommendationById(Integer id);

    @Query("select r from Recommendation r where r.jobApplication.jobSeeker.id=?1")
    List<Recommendation> getrecommendations(Integer jobSeeker_id);



}
