package com.example.tamakanfp.Controller;
import java.io.IOException;


import com.example.tamakanfp.Model.User;
import com.example.tamakanfp.Service.CertificatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
public class CertificatesController {

    @Autowired
    private CertificatesService certificatesService ;

    @GetMapping("/get-certificate-by-jobSeekerid/{jobSeekr_id}")
    public ResponseEntity getCertificatebyjobSeeker(@AuthenticationPrincipal User user, @PathVariable Integer jobSeekr_id){
        return ResponseEntity.status(200).body(certificatesService.getCertificatesByJobSeekerid(user.getId(),jobSeekr_id));

    }

    @PostMapping("/add-certificate/{job_application}")
    public ResponseEntity<?> uploadImage( @AuthenticationPrincipal User user,@PathVariable Integer job_application, @RequestParam("file")MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(certificatesService.uploadFile(user.getId(),job_application,file));
    }


    //Authintcatio jobseeker job provider
    @GetMapping("/download-certificate/{fileName}")
    public ResponseEntity<?> downloadImage(@AuthenticationPrincipal User user,@PathVariable String fileName){
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(MediaType.APPLICATION_PDF_VALUE))
                .body(certificatesService.downloadFile(user.getId(), fileName));

    }

}





