package com.example.tamakanfp.Config;



        import com.example.tamakanfp.Service.MyUserDetailsService;
        import lombok.RequiredArgsConstructor;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
        import org.springframework.security.config.http.SessionCreationPolicy;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
        import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MyUserDetailsService myUserDetailsService;


    @Bean
    public DaoAuthenticationProvider authenticationProvider (){
        DaoAuthenticationProvider authenticationProvider =new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(myUserDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authenticationProvider;
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{

        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)

                .and()


                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/jobSeeker/regester").permitAll()
                .requestMatchers("/api/v1/jobProvider/regester").permitAll()
                .requestMatchers("/api/v1/jobSeeker/add-job-seeker-profile/{seekerId}").hasAuthority("seeker")
                .requestMatchers("/api/v1/job/add-job/{jobProviderId}").hasAuthority("provider")
                .requestMatchers("api/v1/job-application/add-job-application/{jobId}/{seekerId}").hasAuthority("seeker")


                .requestMatchers("/api/v1/recommendation/add-recommendation/{jobApp_id}").permitAll()
                .requestMatchers("/api/v1/recommendation/get-all-recommendation-by-id/{jobS_id}").hasAnyAuthority("seeker","provider")
                .requestMatchers("/api/v1/files/get-certificate-by-jobSeekerid/{jobSeekr_id}").hasAnyAuthority("seeker","provider")
                .requestMatchers("/api/v1/files/add-certificate/{job_application}").hasAuthority("provider")
                .requestMatchers("/api/v1/files/download-certificate/{fileName}").hasAuthority("seeker")
                .requestMatchers("/api/v1/job/get-by-salary/{salary}").permitAll()
                .requestMatchers("/api/v1/job/get-by-id/{job_id}").permitAll()
                .requestMatchers("/api/v1/job/get-by-name/{jobName}").permitAll()
                .requestMatchers("/api/v1/job/get-by-city/{city}").permitAll()
                .requestMatchers("/api/v1/job/get-by-endDate/{endDate}").permitAll()
                .requestMatchers("api/v1/job/count-all-jobs").permitAll()
                .requestMatchers("api/v1/job/count-job-salary/{salary}").permitAll()
                .requestMatchers("api/v1/job/count-job-name/{name}").permitAll()
                .requestMatchers("api/v1/job/count-job-city/{city}").permitAll()
                .requestMatchers("api/v1/job/count-job-enddate/{enddate}").permitAll()







                .anyRequest().authenticated()



                .and()


                .logout().logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)


                .and()
                .httpBasic();
        return http.build();

    }












}