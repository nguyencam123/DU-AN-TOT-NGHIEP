package com.example.demo.cors.homestayowner.service;

public interface HomestayOwnerForgetPassService {
    void sendResetPasswordEmail(String username);

    void resetPasswordWithToken(String resetPasswordToken, String newPassword);

}
