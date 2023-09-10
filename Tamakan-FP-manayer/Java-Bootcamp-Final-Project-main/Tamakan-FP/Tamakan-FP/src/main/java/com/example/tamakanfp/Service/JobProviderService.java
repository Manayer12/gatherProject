package com.example.tamakanfp.Service;


import com.example.tamakanfp.ApiResponse.ApiException;
import com.example.tamakanfp.DTO.JobProviderDTO;
import com.example.tamakanfp.Model.JobProvider;
import com.example.tamakanfp.Model.User;
import com.example.tamakanfp.Repository.JobProviderRepository;
import com.example.tamakanfp.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobProviderService {

    private final JobProviderRepository jobProviderRepository;
    private final UserRepository userRepository;


    public void regester(JobProviderDTO jobProviderDTO)
    {

        String hash = new BCryptPasswordEncoder().encode(jobProviderDTO.getPassword());


        User user1 = new User(null,jobProviderDTO.getUsername(), hash, jobProviderDTO.getEmail(),"provider",null,null);
        JobProvider jobProvider=new JobProvider(null,jobProviderDTO.getName(),jobProviderDTO.getPhoneNumber(),jobProviderDTO.getCity(),jobProviderDTO.getAddress(),jobProviderDTO.getSector(),jobProviderDTO.getLicense(),jobProviderDTO.getStatus(),user1,null);
        user1.setJopProvider(jobProvider);

        jobProviderRepository.save(jobProvider);
        userRepository.save(user1);
    }

    public  void  updateJobProvider (Integer user_id ,Integer id ,JobProviderDTO jobProviderdto){
        User user = userRepository.findUserById(user_id);
        JobProvider oldjobProvider= jobProviderRepository.findJopProviderById(id);
        if (user==null){
            throw new ApiException("user not found");
        }
        if (oldjobProvider == null) {
            throw new ApiException("job Provider is null");
        }

        if (oldjobProvider.getUser().getId() != user_id){
            throw new ApiException("user is not allowed");
        }

        user.setUsername(jobProviderdto.getUsername());
        user.setPassword(jobProviderdto.getPassword());
        user.setEmail(jobProviderdto.getEmail());
        user.setRole(jobProviderdto.getRole());
        oldjobProvider.setUser(user);
        oldjobProvider.setName(jobProviderdto.getName());
        oldjobProvider.setPhoneNumber(jobProviderdto.getPhoneNumber());
        oldjobProvider.setCity(jobProviderdto.getCity());
        oldjobProvider.setAddress(jobProviderdto.getAddress());
        oldjobProvider.setSector(oldjobProvider.getSector());
        oldjobProvider.setLicense(oldjobProvider.getLicense());

        jobProviderRepository.save(oldjobProvider);

    }




//    private final AgentRepository agentRepository;
//    private final UserRepository userRepository;
//
//    public void regester(AgentDTO agentDTO)
//    {
//
//        String hash = new BCryptPasswordEncoder().encode(agentDTO.getPassword());
//
//        User user1 = new User(null,agentDTO.getUsername(), hash, "Agent",null,null);
//        Agent agent =new Agent(null,user1,null,null);
//        user1.setAgent(agent);
//
//        agentRepository.save(agent);
//        userRepository.save(user1);
//    }
}
