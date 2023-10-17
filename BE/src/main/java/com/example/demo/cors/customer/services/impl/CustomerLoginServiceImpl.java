package com.example.demo.cors.customer.services.impl;

import com.example.demo.cors.customer.model.request.CustomerLoginRequest;
import com.example.demo.cors.customer.model.response.CustomerLoginResponse;
import com.example.demo.cors.customer.repository.CustomerLoginRepository;
import com.example.demo.cors.customer.services.CustomerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerLoginServiceImpl implements CustomerLoginService {

    @Autowired
    private CustomerLoginRepository customerLoginRepository;

    @Override
    public CustomerLoginResponse getCustomerLogin(CustomerLoginRequest customerLoginRequest) {
        return customerLoginRepository.getCustomerLogin(customerLoginRequest);
    }
}
