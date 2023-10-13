package com.example.demo.cors.login.service;

import com.example.demo.cors.login.model.response.SignInResponse;
import com.example.demo.cors.login.model.resquest.SignInRequest;

public interface LoginService {

    SignInResponse  getLogin(SignInRequest signInRequest);
}
