package com.example.demo.cors.login.service.impl;

import com.example.demo.cors.login.model.resquest.SignInRequest;
import com.example.demo.cors.login.repository.LoginRepository;
import com.example.demo.cors.login.model.response.SignInResponse;
import com.example.demo.cors.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public SignInResponse getLogin(SignInRequest signInRequest) {
        return loginRepository.getLogin(signInRequest);
    }
}
