package com.example.demo.cors.admin.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminLoginRequest {
    private String pass;

    private String username;
}
