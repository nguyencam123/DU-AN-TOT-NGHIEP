package com.example.demo.cors.admin.services.impl;


import com.example.demo.cors.admin.model.request.AdminOwnerHomestayAppRequest;
import com.example.demo.cors.admin.model.request.AdminOwnerHomestayRequest;
import com.example.demo.cors.admin.repository.AdminHomestayRepository;
import com.example.demo.cors.admin.repository.AdminOwnerHomestayRepository;
import com.example.demo.cors.admin.services.AdminOwnerHomestayService;
import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.entities.ApprovalHistory;
import com.example.demo.entities.Homestay;
import com.example.demo.entities.OwnerHomestay;
import com.example.demo.infrastructure.configemail.Email;
import com.example.demo.infrastructure.configemail.EmailSender;
import com.example.demo.infrastructure.contant.Message;
import com.example.demo.infrastructure.contant.Status;
import com.example.demo.infrastructure.contant.StatusApproval;
import com.example.demo.infrastructure.exception.rest.RestApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminOwnerHomestayServiceImpl implements AdminOwnerHomestayService {
    @Autowired
    private AdminOwnerHomestayRepository adminOwnerHomestayRepository;

    @Autowired
    private AdminHomestayRepository adminHomestayRepository;

    @Autowired
    private EmailSender emailSender;

    @Override
    public PageableObject<OwnerHomestay> getAllOwner(AdminOwnerHomestayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<OwnerHomestay> adminOwnerHomestay = adminOwnerHomestayRepository.getAllOwner(pageable, request);
        return new PageableObject<>(adminOwnerHomestay);
    }

    @Override
    public OwnerHomestay adminApprovalOwnerHomestay(AdminOwnerHomestayAppRequest request) {
        Optional<OwnerHomestay> optional = adminOwnerHomestayRepository.findById(request.getOwnerHomestayId());
        if (!optional.isPresent()) {
            throw new RestApiException(Message.NOT_EXISTS);
        }
        OwnerHomestay ownerHomestay = optional.get();
        ownerHomestay.setStatus(Status.HOAT_DONG);
        adminOwnerHomestayRepository.save(ownerHomestay);

        Email email = new Email();
        email.setToEmail(new String[]{ownerHomestay.getEmail()});
        email.setSubject("Tài Khoản TravelVivu");
        email.setTitleEmail("Chúc mừng " + ownerHomestay.getName());
        email.setBody("Tài Khoản " + ownerHomestay.getName() + " của bạn đã được hoạt động trở lại");
        emailSender.sendEmail(email.getToEmail(), email.getSubject(), email.getTitleEmail(), email.getBody());
        return ownerHomestay;
    }

    @Override
    public OwnerHomestay adminRefuseOwnerHomestay(AdminOwnerHomestayAppRequest request) {
        Optional<OwnerHomestay> optional = adminOwnerHomestayRepository.findById(request.getOwnerHomestayId());
        if (!optional.isPresent()) {
            throw new RestApiException(Message.NOT_EXISTS);
        }

        OwnerHomestay ownerHomestay = optional.get();
        Homestay homestay = adminHomestayRepository.findHomestayBy(ownerHomestay.getId());
        homestay.setStatus(Status.KHONG_HOAT_DONG);
        ownerHomestay.setStatus(Status.KHONG_HOAT_DONG);
        adminOwnerHomestayRepository.save(ownerHomestay);

        Email email = new Email();
        email.setToEmail(new String[]{ownerHomestay.getEmail()});
        email.setSubject("Tài Khoản TravelVivu");
        email.setTitleEmail("Xin Lỗi " + ownerHomestay.getName());
        email.setBody("Tài Khoản " + ownerHomestay.getName() + " của bạn đã bị khóa vui lòng liên hệ với chúng tôi để được mở khóa");
        emailSender.sendEmail(email.getToEmail(), email.getSubject(), email.getTitleEmail(), email.getBody());
        return ownerHomestay;
    }
}
