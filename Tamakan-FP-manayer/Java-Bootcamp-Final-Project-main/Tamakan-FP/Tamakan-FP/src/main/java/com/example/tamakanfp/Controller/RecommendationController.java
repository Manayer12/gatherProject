package com.example.tamakanfp.Controller;



import com.example.tamakanfp.ApiResponse.ApiResponse;
import com.example.tamakanfp.DTO.RecommendationDTO;
import com.example.tamakanfp.Model.User;

import com.example.tamakanfp.Service.RecommendationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recommendation")
public class RecommendationController {


    private final RecommendationService recommendationService;

    @GetMapping("/get-all-recommendation-by-id/{jobS_id}")
    public ResponseEntity getAllrecommendation(@AuthenticationPrincipal User user,@PathVariable Integer jobS_id ){
        return ResponseEntity.status(200).body(recommendationService.getAllRecommendByJobSeekerid(user.getId(),jobS_id));
    }


    @PostMapping("/add-recommendation/{jobApp_id}")
    public ResponseEntity addRecommendation(@AuthenticationPrincipal User user , @PathVariable Integer jobApp_id, @RequestBody @Valid RecommendationDTO recommendationDTO ){
        recommendationService.addRecommendation(user.getId(), jobApp_id,recommendationDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Recommendation added"));
    }


}
