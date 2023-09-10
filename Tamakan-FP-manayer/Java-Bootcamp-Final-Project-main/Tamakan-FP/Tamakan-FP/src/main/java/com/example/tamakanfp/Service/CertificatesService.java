package com.example.tamakanfp.Service;

import com.example.tamakanfp.ApiResponse.ApiException;
import com.example.tamakanfp.Model.*;
import com.example.tamakanfp.Repository.*;
import com.example.tamakanfp.Util.FileUtils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CertificatesService {
    @Autowired
    private CertificatesRepository certificatesRepository;
    private final JobApplicationRepository jobApplicationRepository;
    private final JobSeekerRepository jobSeekerRepository;
    private final UserRepository userRepository;
    private final JobProviderRepository jobProviderRepository;

    //add authentication to job provider to add certificates and recommendation
    //All the users are able to see the certificate
    public List<Certificates> getCertificatesByJobSeekerid(Integer user_id,Integer jobSeeker_id){
        User user=userRepository.findUserById(user_id);
        if (user==null){
            throw new ApiException("User id is null");
        }
//        JobSeeker jobSeeker=jobSeekerRepository.findJopSeekerById(user_id);
//
//        if ( jobSeeker==null) {
//
//            throw new ApiException("jobSeeker is null");
//        }
        List<Certificates> certificatesList=certificatesRepository.findCertificatesByJobApplication_JobSeekerId(jobSeeker_id);

        if ( certificatesList.isEmpty() ) {

            throw new ApiException("No Certificates yet");
        }

        return certificatesList;

    }



    public String uploadFile(Integer user_id,Integer jobapplication_id,MultipartFile file) throws IOException {
        User user=userRepository.findUserById(user_id);
        if (user==null){
            throw new ApiException("User id is null");
        }

        JobProvider jobProvider1=jobProviderRepository.findJopProviderById(user_id);
        if (jobProvider1 == null ) {
            throw new ApiException("Job Provider  is null");
        }

        JobApplication jobsApp=jobApplicationRepository.findJobApplicationById(jobapplication_id);

        if (jobsApp == null ) {
            throw new ApiException("Job Application id is null");
        }

        LocalDate currentDate = LocalDate.now();
        if (!Objects.equals(jobsApp.getStatus(), "accepted") && !jobsApp.getJob().getEndDate().isBefore(currentDate)){
            throw new ApiException("job application status is not accepted or the job period is not done yet! ");
        }

        Certificates imageData = certificatesRepository.save(Certificates.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .fileData(FileUtils.compressFile(file.getBytes())).jobApplication(jobsApp).build());


        if (imageData == null) {
            throw new ApiException(" no certificates for user");
        }
        imageData.setJobApplication(jobsApp);
        certificatesRepository.save(imageData);

        return "file uploaded successfully : " + file.getOriginalFilename();



    }

    public byte[] downloadFile(Integer user_id,String fileName){
        User user=userRepository.findUserById(user_id);
        if (user==null){
            throw new ApiException("user id is null");
        }

        Optional<Certificates> dbImageData = certificatesRepository.findByName(fileName);

        byte[] files=FileUtils.decompressFile(dbImageData.get().getFileData());
        return files;
    }



}




