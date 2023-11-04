package com.example.demo.cors.homestayowner.model.request.loginrequest;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HomestayOwnerPasswordRequest {

    private String currentPassword;

    private String newPassword;

    private String confirmationPassword;

}
