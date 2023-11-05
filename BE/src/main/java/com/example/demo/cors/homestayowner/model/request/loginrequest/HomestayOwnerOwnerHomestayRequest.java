package com.example.demo.cors.homestayowner.model.request.loginrequest;

import com.example.demo.infrastructure.contant.Status;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HomestayOwnerOwnerHomestayRequest {

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
