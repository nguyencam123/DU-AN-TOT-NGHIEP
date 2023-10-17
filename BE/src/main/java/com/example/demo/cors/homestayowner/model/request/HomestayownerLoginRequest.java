package com.example.demo.cors.homestayowner.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HomestayownerLoginRequest {

    private String username;

    private String password;

}
