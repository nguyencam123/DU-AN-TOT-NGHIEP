package com.example.demo.cors.admin.services.impl;

import com.example.demo.cors.admin.model.request.AdminRequest;
import com.example.demo.cors.admin.model.request.AdminUserPasswordRequest;
import com.example.demo.cors.admin.model.response.AdminAuthenticationReponse;
import com.example.demo.cors.admin.model.response.AdminLoginResponse;
import com.example.demo.cors.admin.model.request.AdminLoginRequest;
import com.example.demo.cors.admin.repository.AdminLoginRepository;
import com.example.demo.cors.admin.services.AdminLoginService;
import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerAuthenticationReponse;
import com.example.demo.entities.Admin;
import com.example.demo.infrastructure.contant.Status;
import com.example.demo.infrastructure.exception.rest.RestApiException;
import com.example.demo.infrastructure.security.token.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AdminLoginServiceImpl implements AdminLoginService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AdminLoginRepository adminLoginRepository;

    @Override
    public AdminLoginResponse getAdLogin(AdminLoginRequest adminLoginRequest) {
        return adminLoginRepository.getLogin(adminLoginRequest);
    }

    @Override
    public AdminAuthenticationReponse register(AdminRequest request) {
        if (isNullOrEmpty(request.getUsername())) {
            throw new RestApiException("Username cannot be empty");
        }
        if (isNullOrEmpty(request.getName())) {
            throw new RestApiException("Name cannot be empty");
        }
        if (request.getBirthday() == null) {
            throw new RestApiException("Birthday cannot be empty");
        }
        if (isNullOrEmpty(request.getAddress())) {
            throw new RestApiException("Address cannot be empty");
        }
        if (isNullOrEmpty(request.getPhoneNumber())) {
            throw new RestApiException("Phone number cannot be empty");
        }
        if (isNullOrEmpty(request.getEmail())) {
            throw new RestApiException("Email cannot be empty");
        }
        if (isNullOrEmpty(request.getPassword())) {
            throw new RestApiException("Password cannot be empty");
        }
        Admin admin = new Admin();
        Random random = new Random();
        int number = random.nextInt(1000);
        String code = String.format("G%04d", number);
        admin.setCode(code);
        if (adminLoginRepository.existsByUsername(request.getUsername())) {
            throw new RestApiException("Username is already in use");
        }
        if (adminLoginRepository.existsByEmail(request.getEmail())) {
            throw new RestApiException("Email is already in use");
        }
        admin.setName(request.getName());
        admin.setBirthday(request.getBirthday());
        admin.setGender(request.getGender());
        admin.setAddress(request.getAddress());
        admin.setPhoneNumber(request.getPhoneNumber());
        admin.setEmail(request.getEmail());
        admin.setUsername(request.getUsername());
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        admin.setStatus(Status.HOAT_DONG);
        adminLoginRepository.save(admin);
        var jwtServices = jwtService.generateToken(admin);
        return AdminAuthenticationReponse.builder().
                token(jwtServices)
                .id(admin.getId())
                .code(admin.getCode())
                .name(admin.getName())
                .birthday(admin.getBirthday())
                .gender(admin.getGender())
                .address(admin.getAddress())
                .phoneNumber(admin.getPhoneNumber())
                .email(admin.getEmail())
                .username(admin.getUsername())
                .status(admin.getStatus()).build();
    }

    @Override
    public AdminAuthenticationReponse authenticate(AdminUserPasswordRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var admin = adminLoginRepository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(admin);
        System.err.println(jwtToken);
        return AdminAuthenticationReponse.builder().
                token(jwtToken)
                .id(admin.getId())
                .code(admin.getCode())
                .name(admin.getName())
                .birthday(admin.getBirthday())
                .gender(admin.getGender())
                .address(admin.getAddress())
                .phoneNumber(admin.getPhoneNumber())
                .email(admin.getEmail())
                .username(admin.getUsername())
                .status(admin.getStatus()).build();
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

}
