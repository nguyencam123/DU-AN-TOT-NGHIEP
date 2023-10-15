package com.example.demo.cors.login.service;

import com.example.demo.cors.login.model.response.SignInResponse;
import com.example.demo.cors.login.model.resquest.SignInRequest;

import java.util.List;

public interface LoginService {

    SignInResponse getLogin(SignInRequest signInRequest);
}
