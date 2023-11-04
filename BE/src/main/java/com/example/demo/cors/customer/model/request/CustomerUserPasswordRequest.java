package com.example.demo.cors.customer.model.request;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerUserPasswordRequest {
    private String username;
    private String password;
}
