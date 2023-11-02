package com.example.demo.cors.homestayowner.service.impl;

import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerAuthenticationReponse;
import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerLoginReponse;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerOwnerHomestayRequest;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerUsenamePasswordRequest;
import com.example.demo.cors.homestayowner.model.request.HomestayownerLoginRequest;
import com.example.demo.cors.homestayowner.repository.HomestayOwnerOwnerHomestayRepository;
import com.example.demo.cors.homestayowner.service.HomestayOwnerLoginService;
import com.example.demo.entities.OwnerHomestay;
import com.example.demo.infrastructure.contant.Status;
import com.example.demo.infrastructure.exception.rest.RestApiException;
import com.example.demo.infrastructure.security.token.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class HomestayOwnerLoginServiceImpl implements HomestayOwnerLoginService {

    @Autowired
    private HomestayOwnerOwnerHomestayRepository homestayownerOwnerHomestayRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public HomestayOwnerLoginReponse login(@RequestBody HomestayownerLoginRequest homestayownerLoginRequest) {
        return homestayownerOwnerHomestayRepository.getLoginOwnerHomestay(homestayownerLoginRequest);
    }

    @Override
    public HomestayOwnerAuthenticationReponse register(HomestayOwnerOwnerHomestayRequest request) {
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
        OwnerHomestay ownerHomestay=new OwnerHomestay();
        Random random = new Random();
        int number = random.nextInt(1000);
        String code=String.format("G%04d",number);
        ownerHomestay.setCode(code);
        if (homestayownerOwnerHomestayRepository.existsByUsername(request.getUsername())) {
            throw new RestApiException("Username is already in use");
        }
        if (homestayownerOwnerHomestayRepository.existsByEmail(request.getEmail())) {
            throw new RestApiException("Email is already in use");
        }
        ownerHomestay.setName(request.getName());
        ownerHomestay.setBirthday(request.getBirthday());
        ownerHomestay.setGender(request.getGender());
        ownerHomestay.setAddress(request.getAddress());
        ownerHomestay.setPhoneNumber(request.getPhoneNumber());
        ownerHomestay.setEmail(request.getEmail());
        ownerHomestay.setUsername(request.getUsername());
        ownerHomestay.setPassword(passwordEncoder.encode(request.getPassword()));
        ownerHomestay.setStatus(Status.HOAT_DONG);
        homestayownerOwnerHomestayRepository.save(ownerHomestay);
        var jwtServices=jwtService.generateToken(ownerHomestay);
        return HomestayOwnerAuthenticationReponse.builder().
                token(jwtServices)
                .id(ownerHomestay.getId())
                .code(ownerHomestay.getCode())
                .name(ownerHomestay.getName())
                .birthday(ownerHomestay.getBirthday())
                .gender(ownerHomestay.getGender())
                .address(ownerHomestay.getAddress())
                .phoneNumber(ownerHomestay.getPhoneNumber())
                .email(ownerHomestay.getEmail())
                .username(ownerHomestay.getUsername())
                .status(ownerHomestay.getStatus()).build();
    }

    @Override
    public HomestayOwnerAuthenticationReponse authenticate(HomestayOwnerUsenamePasswordRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user=homestayownerOwnerHomestayRepository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken=jwtService.generateToken(user);
        return HomestayOwnerAuthenticationReponse.builder()
                .token(jwtToken)
                .code(user.getCode())
                .name(user.getName())
                .birthday(user.getBirthday())
                .gender(user.getGender())
                .address(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .username(user.getUsername())
                .status(user.getStatus())
                .build();
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

}
