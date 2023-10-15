package com.example.demo.cors.login.controller;

import com.example.demo.cors.login.model.response.SignInResponse;
import com.example.demo.cors.login.model.resquest.SignInRequest;
import com.example.demo.cors.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/login")
public class LoginContorller {

    @Autowired
    private LoginService loginService;

    @PostMapping("")
    public SignInResponse getLogin(@RequestBody SignInRequest signInRequest){
        return  loginService.getLogin(signInRequest);
    }
}
