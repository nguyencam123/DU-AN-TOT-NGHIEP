package com.example.demo.cors.admin.services.impl;

import com.example.demo.cors.admin.model.request.AdminRequest;
import com.example.demo.cors.admin.repository.AdminHomestayRepository;
import com.example.demo.cors.admin.services.AdminApproveHomestayService;
import com.example.demo.entities.ApprovalHistory;
import com.example.demo.entities.Homestay;
import com.example.demo.infrastructure.configemail.Email;
import com.example.demo.infrastructure.configemail.EmailSender;
import com.example.demo.infrastructure.contant.Message;
import com.example.demo.infrastructure.contant.Status;
import com.example.demo.infrastructure.exception.rest.RestApiException;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.repositories.ApprovalHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminApproveHomestayServiceImpl implements AdminApproveHomestayService {

    @Autowired
    private AdminHomestayRepository adminHomestayRepository;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private ApprovalHistoryRepository approvalHistoryRepository;
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Homestay adminApproveHomestay(String id, AdminRequest request) {
        Optional<Homestay> optional = adminHomestayRepository.findById(id);
        if (!optional.isPresent()) {
            throw new RestApiException(Message.NOT_EXISTS);
        }
        Homestay homestay = optional.get();
        homestay.setStatus(Status.HOAT_DONG);
        adminHomestayRepository.save(homestay);

        ApprovalHistory approvalHistory = new ApprovalHistory();
        approvalHistory.setAdmin(adminRepository.findByUsername(request.getUsername()).get());
        approvalHistory.setHomestay(homestay);
        approvalHistoryRepository.save(approvalHistory);

        Email email = new Email();
        email.setToEmail(new String[]{homestay.getOwnerHomestay().getEmail()});
        email.setSubject("Yêu cầu phê duyệt đã được chấp nhận");
        email.setTitleEmail("Chúc mừng " + homestay.getOwnerHomestay().getName());
        email.setBody("Homestay " + homestay.getName() + " của bạn đã được phê duyệt");
        emailSender.sendEmail(email.getToEmail(), email.getSubject(), email.getTitleEmail(), email.getBody());
        return homestay;
    }

}
