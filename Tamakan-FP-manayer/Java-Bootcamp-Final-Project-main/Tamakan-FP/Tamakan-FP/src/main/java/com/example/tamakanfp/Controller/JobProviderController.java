package com.example.tamakanfp.Controller;



import com.example.tamakanfp.ApiResponse.ApiResponse;
import com.example.tamakanfp.DTO.JobProviderDTO;
import com.example.tamakanfp.Service.JobProviderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/jobProvider")
public class JobProviderController {

       private final JobProviderService jobProviderService;


    @PostMapping("/regester")
    public ResponseEntity Regester(@RequestBody @Valid JobProviderDTO jobProviderDTO) {
        jobProviderService.regester(jobProviderDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Job Provider added"));

    }

    @PutMapping("/updateJobProvider/{user_id}/{id}")
    public  ResponseEntity updateJobProvider (@PathVariable Integer user_id,@PathVariable Integer id, @RequestBody @Valid JobProviderDTO jobProviderDTO ){
        jobProviderService.updateJobProvider(user_id,id,jobProviderDTO);
        return ResponseEntity.status(200).body(new ApiResponse("JobProvider updated successfully"));
    }
    @GetMapping("/logout")
    public ResponseEntity Logoout() {
        return ResponseEntity.status(200).body(new ApiResponse("Log out successful"));

    }


}
