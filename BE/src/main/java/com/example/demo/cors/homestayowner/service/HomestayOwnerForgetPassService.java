package com.example.demo.cors.homestayowner.service;

public interface HomestayOwnerForgetPassService {
    void sendResetPasswordEmail(String username, String resetPasswordToken);

    void resetPasswordWithToken(String resetPasswordToken, String newPassword);

    boolean isResetPasswordTokenValid(String resetPasswordToken);

    String createResetPasswordToken(String username);

}
