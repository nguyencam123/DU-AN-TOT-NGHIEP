package com.example.demo.cors.customer.model.request;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerUserPassRequest {
    private String username;
    private String password;
}
