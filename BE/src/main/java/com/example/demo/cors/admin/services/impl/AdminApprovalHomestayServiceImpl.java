package com.example.demo.cors.admin.services.impl;

import com.example.demo.cors.admin.model.request.AdminApprovalRequest;
import com.example.demo.cors.admin.repository.AdminApprovalRepository;
import com.example.demo.cors.admin.repository.AdminHomestayRepository;
import com.example.demo.cors.admin.services.AdminApprovalHomestayService;
import com.example.demo.entities.ApprovalHistory;
import com.example.demo.entities.Homestay;
import com.example.demo.infrastructure.configemail.Email;
import com.example.demo.infrastructure.configemail.EmailSender;
import com.example.demo.infrastructure.contant.StatusApproval;
import com.example.demo.infrastructure.contant.Message;
import com.example.demo.infrastructure.contant.Status;
import com.example.demo.infrastructure.exception.rest.RestApiException;
import com.example.demo.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminApprovalHomestayServiceImpl implements AdminApprovalHomestayService {

    @Autowired
    private AdminHomestayRepository adminHomestayRepository;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private AdminApprovalRepository approvalHistoryRepository;
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Homestay adminApprovalHomestay(AdminApprovalRequest request) {
        Optional<Homestay> optional = adminHomestayRepository.findById(request.getHomestayId());
        if (!optional.isPresent()) {
            throw new RestApiException(Message.NOT_EXISTS);
        }
        Homestay homestay = optional.get();
        homestay.setStatus(Status.HOAT_DONG);
        adminHomestayRepository.save(homestay);

        ApprovalHistory approvalHistory = new ApprovalHistory();
        approvalHistory.setAdmin(adminRepository.findById(request.getAdminId()).get());
        approvalHistory.setHomestay(homestay);
        approvalHistory.setStatus(StatusApproval.PHE_DUYET);
        approvalHistoryRepository.save(approvalHistory);

        Email email = new Email();
        email.setToEmail(new String[]{homestay.getOwnerHomestay().getEmail()});
        email.setSubject("Yêu cầu phê duyệt");
        email.setTitleEmail("Chúc mừng " + homestay.getOwnerHomestay().getName());
        email.setBody("Homestay " + homestay.getName() + " của bạn đã được phê duyệt");
        emailSender.sendEmail(email.getToEmail(), email.getSubject(), email.getTitleEmail(), email.getBody());
        return homestay;
    }

    @Override
    public Homestay adminRefuseHomestay(AdminApprovalRequest request) {
        Optional<Homestay> optional = adminHomestayRepository.findById(request.getHomestayId());
        if (!optional.isPresent()) {
            throw new RestApiException(Message.NOT_EXISTS);
        }
        Homestay homestay = optional.get();
        homestay.setStatus(Status.TU_CHOI_DUYET);
        adminHomestayRepository.save(homestay);

        ApprovalHistory approvalHistory = new ApprovalHistory();
        approvalHistory.setAdmin(adminRepository.findById(request.getAdminId()).get());
        approvalHistory.setHomestay(homestay);
        approvalHistory.setStatus(StatusApproval.TU_CHOI);
        approvalHistoryRepository.save(approvalHistory);

        Email email = new Email();
        email.setToEmail(new String[]{homestay.getOwnerHomestay().getEmail()});
        email.setSubject("Yêu cầu phê duyệt");
        email.setTitleEmail("Kính gửi " + homestay.getOwnerHomestay().getName());
        email.setBody("Homestay " + homestay.getName() + " của bạn đã bị từ chối. Lý do: " + request.getDesc());
        emailSender.sendEmail(email.getToEmail(), email.getSubject(), email.getTitleEmail(), email.getBody());
        return homestay;
    }

}
