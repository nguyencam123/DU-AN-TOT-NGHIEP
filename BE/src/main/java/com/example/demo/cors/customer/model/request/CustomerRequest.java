package com.example.demo.cors.customer.model.request;

import com.example.demo.infrastructure.contant.Status;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequest {


    private String name;

    private Long birthday;

    private Boolean gender;

    private String address;

    private String phoneNumber;

    private String email;

    private String username;

    private String password;

    private String identificationNumber;

    private Integer point;

    private Status status;

}