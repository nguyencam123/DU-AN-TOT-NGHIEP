package com.example.demo.cors.customer.services;

import com.example.demo.cors.customer.model.request.CustomerLoginRequest;
import com.example.demo.cors.customer.model.response.CustomerLoginResponse;

public interface CustomerLoginService {

    CustomerLoginResponse getCustomerLogin(CustomerLoginRequest customerLoginRequest);
}
