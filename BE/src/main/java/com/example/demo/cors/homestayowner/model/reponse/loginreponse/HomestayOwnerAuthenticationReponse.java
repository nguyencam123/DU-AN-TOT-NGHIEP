package com.example.demo.cors.homestayowner.model.reponse.loginreponse;

import com.example.demo.infrastructure.contant.Status;
import com.example.demo.infrastructure.contant.role.RoleOwner;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HomestayOwnerAuthenticationReponse {

    private String id;

    private String token;

    private String refreshToken;

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

    private RoleOwner roleOwner;

    private String accessToken;

}
