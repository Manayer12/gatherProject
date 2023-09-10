package com.example.tamakanfp.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Collections;

@Data
@AllArgsConstructor
@Entity
@RequiredArgsConstructor
//implements UserDetails
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Username  is required")
    @Size(min=5,message = "The Username have to be more than 5 litters")
    @Column(unique = true)
    private String username;

    @NotEmpty(message = "Password is required")
    @Size(min=6,message = "The password have to be more than 5 Char")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z]).{6,}$" , message = "the password must have at least 1 number, 1 letter (upper or lower case) and min 6 char")
    private String password;

    @NotEmpty(message = "Email is required")
    @Email
    @Column(unique = true)
    private String email;


    private String role;

    //Relations
    @OneToOne(cascade =CascadeType.ALL,mappedBy = "user")
    @PrimaryKeyJoinColumn
    JobSeeker jopSeeker;


    @OneToOne(cascade =CascadeType.ALL,mappedBy = "user")
    @PrimaryKeyJoinColumn
    JobProvider jopProvider;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
