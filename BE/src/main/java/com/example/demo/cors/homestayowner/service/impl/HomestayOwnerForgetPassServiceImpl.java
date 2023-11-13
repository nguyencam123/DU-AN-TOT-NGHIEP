package com.example.demo.cors.homestayowner.service.impl;

import com.example.demo.cors.homestayowner.repository.HomestayOwnerOwnerHomestayRepository;
import com.example.demo.cors.homestayowner.service.HomestayOwnerForgetPassService;
import com.example.demo.entities.OwnerHomestay;
import com.example.demo.infrastructure.configemail.Email;
import com.example.demo.infrastructure.configemail.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HomestayOwnerForgetPassServiceImpl implements HomestayOwnerForgetPassService {

    @Autowired
    private HomestayOwnerOwnerHomestayRepository homestayownerOwnerHomestayRepository;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String createResetPasswordToken(String username) {
        OwnerHomestay ownerHomestay = homestayownerOwnerHomestayRepository.findByUsername(username).orElse(null);
        if (ownerHomestay != null) {
            String resetPasswordToken = generateResetPasswordToken();
            ownerHomestay.setResetPasswordToken(resetPasswordToken);
            homestayownerOwnerHomestayRepository.save(ownerHomestay);
            return resetPasswordToken;
        }
        return null;
    }

    private String generateResetPasswordToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public boolean isResetPasswordTokenValid(String resetPasswordToken) {
        OwnerHomestay ownerHomestay = homestayownerOwnerHomestayRepository.findByResetPasswordToken(resetPasswordToken).orElse(null);
        if (ownerHomestay != null) {
            return true;
        }
        return false;
    }

    @Override
    public void resetPasswordWithToken(String resetPasswordToken, String newPassword) {
        OwnerHomestay ownerHomestay = homestayownerOwnerHomestayRepository.findByResetPasswordToken(resetPasswordToken).orElse(null);
        if (ownerHomestay != null) {
            ownerHomestay.setPassword(passwordEncoder.encode(newPassword));
            ownerHomestay.setResetPasswordToken(null);
            homestayownerOwnerHomestayRepository.save(ownerHomestay);
        }
    }

    @Override
    public void sendResetPasswordEmail(String username, String resetPasswordToken) {
        OwnerHomestay owner = homestayownerOwnerHomestayRepository.findByUsername(username).orElse(null);
        if (owner != null) {
            String resetPasswordLink = "http://localhost:3000/api/v2/login/reset-password/" + resetPasswordToken;
            String emailBody = "Bạn đã yêu cầu đặt lại mật khẩu. Vui lòng nhấp vào liên kết sau để thực hiện đặt lại mật khẩu: " + resetPasswordLink;

            Email email = new Email();
            email.setToEmail(new String[]{owner.getEmail()});
            email.setSubject("Yêu cầu đặt lại mật khẩu");
            email.setTitleEmail("Đặt lại mật khẩu");
            email.setBody(emailBody);
            emailSender.sendEmail(email.getToEmail(), email.getSubject(), email.getTitleEmail(), email.getBody());
        }
    }

}
