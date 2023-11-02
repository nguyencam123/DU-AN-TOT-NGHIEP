package com.example.demo.cors.admin.model.request;

import com.example.demo.infrastructure.contant.Status;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminRequest {

    private String name;

    private Long birthday;

    private Boolean gender;

    private String address;

    private String phoneNumber;

    private String email;

    private String username;

    private String password;

    private Status status;

}
