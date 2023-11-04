package com.example.demo.cors.customer.services;

import com.example.demo.cors.customer.model.request.CustomerLoginRequest;
import com.example.demo.cors.customer.model.request.CustomerRequest;
import com.example.demo.cors.customer.model.request.CustomerUserPasswordRequest;
import com.example.demo.cors.customer.model.request.CustomerPasswordRequest;
import com.example.demo.cors.customer.model.response.CustomerAuthenticationReponse;
import com.example.demo.cors.customer.model.response.CustomerLoginResponse;
import com.example.demo.cors.homestayowner.model.reponse.loginreponse.HomestayOwnerAuthenticationReponse;

import java.security.Principal;

public interface CustomerLoginService {

    CustomerLoginResponse getCustomerLogin(CustomerLoginRequest customerLoginRequest);

    CustomerAuthenticationReponse CustomerRegister(CustomerRequest customerRequest);

    CustomerAuthenticationReponse CustomerAuthenticate(CustomerUserPasswordRequest request);

    CustomerAuthenticationReponse changePassword(CustomerPasswordRequest request, Principal connecteUser);

}
