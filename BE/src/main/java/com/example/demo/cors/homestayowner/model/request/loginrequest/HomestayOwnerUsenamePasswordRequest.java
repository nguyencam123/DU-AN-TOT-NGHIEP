package com.example.demo.cors.homestayowner.model.request.loginrequest;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomestayOwnerUsenamePasswordRequest {

    private String username;

    private String password;

}
