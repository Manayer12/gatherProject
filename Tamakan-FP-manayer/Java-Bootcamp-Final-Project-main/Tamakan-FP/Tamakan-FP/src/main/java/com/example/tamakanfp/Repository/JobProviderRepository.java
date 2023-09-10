package com.example.tamakanfp.Repository;


import com.example.tamakanfp.Model.JobProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobProviderRepository extends JpaRepository<JobProvider,Integer> {

    JobProvider findJopProviderById(Integer id);


}
