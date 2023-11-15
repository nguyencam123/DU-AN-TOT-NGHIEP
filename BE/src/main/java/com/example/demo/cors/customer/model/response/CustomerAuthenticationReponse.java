package com.example.demo.cors.customer.model.response;


import com.example.demo.infrastructure.contant.Status;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAuthenticationReponse {

    private String id;

    private String token;

    private String code;

    private String name;

    private Long birthday;

    private Boolean gender;

    private String address;

    private String phoneNumber;

    private String email;

    private String username;

    private String avataUrl;

    private Status status;

}
