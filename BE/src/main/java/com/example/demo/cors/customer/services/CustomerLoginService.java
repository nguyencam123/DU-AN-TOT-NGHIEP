package com.example.demo.cors.customer.services;

import com.example.demo.cors.customer.model.request.CustomerForgetRequest;
import com.example.demo.cors.customer.model.request.CustomerPasswordRequest;
import com.example.demo.cors.customer.model.request.CustomerRequest;
import com.example.demo.cors.customer.model.request.CustomerUserPassRequest;
import com.example.demo.cors.customer.model.response.CustomerAuthenticationReponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

public interface CustomerLoginService {

    void confirmEmail(String id);

    CustomerAuthenticationReponse CustomerRegister(CustomerRequest customerRequest);

    CustomerAuthenticationReponse CustomerAuthenticate(CustomerUserPassRequest request);

    CustomerAuthenticationReponse changePassword(CustomerPasswordRequest request, Principal connecteUser);

    CustomerAuthenticationReponse updateInformationCusmoter(String idCustomer, CustomerRequest request) throws IOException;

    void sendResetPasswordEmail(CustomerForgetRequest request);

}
