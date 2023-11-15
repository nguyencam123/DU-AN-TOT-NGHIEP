package com.example.demo.cors.customer.services.impl;

import com.cloudinary.Cloudinary;
import com.example.demo.cors.customer.model.request.CustomerLoginRequest;
import com.example.demo.cors.customer.model.request.CustomerPasswordRequest;
import com.example.demo.cors.customer.model.request.CustomerRequest;
import com.example.demo.cors.customer.model.request.CustomerUserPasswordRequest;
import com.example.demo.cors.customer.model.response.CustomerAuthenticationReponse;
import com.example.demo.cors.customer.model.response.CustomerLoginResponse;
import com.example.demo.cors.customer.repository.CustomerLoginRepository;
import com.example.demo.cors.customer.services.CustomerLoginService;
import com.example.demo.entities.User;
import com.example.demo.infrastructure.contant.Status;
import com.example.demo.infrastructure.exception.rest.RestApiException;
import com.example.demo.infrastructure.security.token.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Service
public class CustomerLoginServiceImpl implements CustomerLoginService {

    @Autowired
    private CustomerLoginRepository customerLoginRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public CustomerLoginResponse getCustomerLogin(CustomerLoginRequest customerLoginRequest) {
        return customerLoginRepository.getCustomerLogin(customerLoginRequest);
    }

    @Override
    public CustomerAuthenticationReponse CustomerRegister(CustomerRequest request) {
        if (isNullOrEmpty(request.getUsername())) {
            throw new RestApiException("Username cannot be empty");
        }
        if (isNullOrEmpty(request.getName())) {
            throw new RestApiException("Name cannot be empty");
        }
        if (request.getBirthday() == null) {
            throw new RestApiException("Birthday cannot be empty");
        }
        if (isNullOrEmpty(request.getAddress())) {
            throw new RestApiException("Address cannot be empty");
        }
        if (isNullOrEmpty(request.getPhoneNumber())) {
            throw new RestApiException("Phone number cannot be empty");
        }
        if (isNullOrEmpty(request.getEmail())) {
            throw new RestApiException("Email cannot be empty");
        }
        if (isNullOrEmpty(request.getPassword())) {
            throw new RestApiException("Password cannot be empty");
        }
        if (customerLoginRepository.existsByUsername(request.getUsername())) {
            throw new RestApiException("Username is already in use");
        }
        if (customerLoginRepository.existsByEmail(request.getEmail())) {
            throw new RestApiException("Email is already in use");
        }
        User user = new User();
        Random random = new Random();
        int number = random.nextInt(1000);
        String code = String.format("G%04d", number);
        user.setCode(code);
        user.setName(request.getName());
        user.setBirthday(request.getBirthday());
        user.setGender(request.getGender());
        user.setAddress(request.getAddress());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setStatus(Status.HOAT_DONG);
        customerLoginRepository.save(user);
        var jwtServices = jwtService.generateToken(user);
        return CustomerAuthenticationReponse.builder()
                .token(jwtServices)
                .id(user.getId())
                .code(user.getCode())
                .name(user.getName())
                .birthday(user.getBirthday())
                .gender(user.getGender())
                .address(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .username(user.getUsername())
                .status(user.getStatus())
                .build();
    }

    @Override
    public CustomerAuthenticationReponse CustomerAuthenticate(CustomerUserPasswordRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = customerLoginRepository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return CustomerAuthenticationReponse.builder()
                .token(jwtToken)
                .id(user.getId())
                .code(user.getCode())
                .name(user.getName())
                .birthday(user.getBirthday())
                .gender(user.getGender())
                .address(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .username(user.getUsername())
                .status(user.getStatus())
                .build();
    }

    @Override
    public CustomerAuthenticationReponse changePassword(CustomerPasswordRequest request, Principal connecteUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connecteUser).getPrincipal();
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        ;
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("password aren't the same");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        customerLoginRepository.save(user);
        return CustomerAuthenticationReponse.builder()
                .id(user.getId())
                .code(user.getCode())
                .name(user.getName())
                .birthday(user.getBirthday())
                .gender(user.getGender())
                .address(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .username(user.getUsername())
                .status(user.getStatus())
                .build();
    }

    @Override
    public CustomerAuthenticationReponse updateInformationCusmoter(String idCustomer, CustomerRequest request, MultipartFile multipartFile) throws IOException {
        if (isNullOrEmpty(request.getName())) {
            throw new IOException("Name cannot be empty");
        }
        if (isNullOrEmpty(request.getEmail())) {
            throw new IOException("Email cannot be empty");
        }
        if (isNullOrEmpty(request.getAddress())) {
            throw new IOException("Address cannot be empty");
        }
        if (isNullOrEmpty(request.getUsername())) {
            throw new IOException("Username cannot be empty");
        }
        if (request.getBirthday() == null) {
            throw new IOException("Birthday cannot be empty");
        }
        if (request.getGender() == null && request.getGender().booleanValue()) {
            throw new IOException("Birthday cannot be empty");
        }
        String phoneNumber = request.getPhoneNumber();
        if (!isValidVietnamesePhoneNumber(phoneNumber)) {
            throw new RestApiException("Invalid Vietnamese phone number format");
        }
        String emails = request.getEmail();
        if (!isValidEmail(emails)) {
            throw new RestApiException("Invalid Email format");
        }
        User customer = customerLoginRepository.findById(idCustomer).get();
        customer.setName(request.getName());
        customer.setBirthday(request.getBirthday());
        customer.setGender(request.getGender());
        customer.setAddress(request.getAddress());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setEmail(request.getEmail());
        customer.setUsername(request.getUsername());
        customer.setAvatarUrl(cloudinary.uploader()
                .upload(multipartFile.getBytes(),
                        Map.of("id", UUID.randomUUID().toString()))
                .get("url")
                .toString());
        customerLoginRepository.save(customer);
        return CustomerAuthenticationReponse.builder()
                .code(customer.getCode())
                .id(customer.getId())
                .name(customer.getName())
                .birthday(customer.getBirthday())
                .gender(customer.getGender())
                .address(customer.getAddress())
                .phoneNumber(customer.getPhoneNumber())
                .email(customer.getEmail())
                .avataUrl(customer.getAvatarUrl())
                .username(customer.getUsername())
                .status(customer.getStatus())
                .build();
    }

    private Boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        return email.matches(regex);
    }

    private boolean isValidVietnamesePhoneNumber(String phoneNumber) {
        String regex = "^(03|05|07|08|09)\\d{8}$";
        return phoneNumber.matches(regex);
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

}
