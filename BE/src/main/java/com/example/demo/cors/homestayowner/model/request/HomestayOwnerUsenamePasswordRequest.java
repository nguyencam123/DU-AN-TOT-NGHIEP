package com.example.demo.cors.homestayowner.model.request;

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
