package com.example.demo.cors.customer.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.customer.model.coventer.CustomerConventer;
import com.example.demo.cors.customer.model.request.CustomerRequest;
import com.example.demo.cors.customer.services.CustomerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerCotroller {

    @Autowired
    private CustomerLoginService customerLoginService;

    @Autowired
    private CustomerConventer conventer;

    @PutMapping("/update-information-owner")
    public ResponseObject updateInformationOwners(@RequestParam("customer") String customerRequest, @RequestParam("id") String idCustomer, @RequestParam("avataUrl") MultipartFile multipartFile) throws IOException {
        CustomerRequest request = conventer.convert(customerRequest);
        return new ResponseObject(customerLoginService.updateInformationCusmoter(idCustomer, request, multipartFile));
    }

}
