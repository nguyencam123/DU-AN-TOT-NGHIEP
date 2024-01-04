package com.example.demo.cors.admin.services.impl;

import com.example.demo.cors.admin.model.request.AdminUserRequest;
import com.example.demo.cors.admin.repository.AdminUserRepository;
import com.example.demo.cors.admin.services.AdminUserService;
import com.example.demo.entities.User;
import com.example.demo.infrastructure.configemail.Email;
import com.example.demo.infrastructure.configemail.EmailSender;
import com.example.demo.infrastructure.contant.Message;
import com.example.demo.infrastructure.contant.Status;
import com.example.demo.infrastructure.exception.rest.RestApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private EmailSender emailSender;

    @Override
    public User adminApprovalUser(AdminUserRequest request) {
        Optional<User> optional = adminUserRepository.findById(request.getUserId());
        if (!optional.isPresent()) {
            throw new RestApiException(Message.NOT_EXISTS);
        }
        User user = optional.get();
        user.setStatus(Status.HOAT_DONG);
        adminUserRepository.save(user);

        Email email = new Email();
        email.setToEmail(new String[]{user.getEmail()});
        email.setSubject("Tài Khoản TravelVivu");
        email.setTitleEmail("Chúc mừng " + user.getName());
        email.setBody("Tài Khoản " + user.getName() + " của bạn đã được hoạt động trở lại");
        emailSender.sendEmail(email.getToEmail(), email.getSubject(), email.getTitleEmail(), email.getBody());

        return user;
    }

    @Override
    public User adminRefuseUser(AdminUserRequest request) {
        Optional<User> optional = adminUserRepository.findById(request.getUserId());
        if (!optional.isPresent()) {
            throw new RestApiException(Message.NOT_EXISTS);
        }
        User user = optional.get();
        user.setStatus(Status.KHONG_HOAT_DONG);
        adminUserRepository.save(user);

        Email email = new Email();
        email.setToEmail(new String[]{user.getEmail()});
        email.setSubject("Tài Khoản TravelVivu");
        email.setTitleEmail("Xin lỗi " + user.getName());
        email.setBody("Tài Khoản " + user.getName() + " của bạn đã bị khóa vui lòng liên hệ chúng tôi để được mở khóa");
        emailSender.sendEmail(email.getToEmail(), email.getSubject(), email.getTitleEmail(), email.getBody());

        return user;
    }
}
