package com.example.demo.cors.customer.services;

import com.example.demo.cors.customer.model.request.CustomerPasswordRequest;
import com.example.demo.cors.customer.model.request.CustomerRequest;
import com.example.demo.cors.customer.model.request.CustomerUserPasswordRequest;
import com.example.demo.cors.customer.model.response.CustomerAuthenticationReponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

public interface CustomerLoginService {

    public void confirmEmail(String id);

    CustomerAuthenticationReponse CustomerRegister(CustomerRequest customerRequest);

    CustomerAuthenticationReponse CustomerAuthenticate(CustomerUserPasswordRequest request);

    CustomerAuthenticationReponse changePassword(CustomerPasswordRequest request, Principal connecteUser);

    CustomerAuthenticationReponse updateInformationCusmoter(String idCustomer, CustomerRequest request, MultipartFile multipartFile) throws IOException;

}
