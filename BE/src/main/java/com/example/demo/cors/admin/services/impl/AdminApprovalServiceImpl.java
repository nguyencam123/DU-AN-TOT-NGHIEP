package com.example.demo.cors.admin.services.impl;

import com.example.demo.cors.admin.model.request.AdminApprovalRequest;
import com.example.demo.cors.admin.repository.AdminApprovalRepository;
import com.example.demo.cors.admin.repository.AdminHomestayRepository;
import com.example.demo.cors.admin.repository.AdminLoginRepository;
import com.example.demo.cors.admin.services.AdminApprovalService;
import com.example.demo.entities.ApprovalHistory;
import com.example.demo.entities.Homestay;
import com.example.demo.infrastructure.configemail.Email;
import com.example.demo.infrastructure.configemail.EmailSender;
import com.example.demo.infrastructure.contant.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminApprovalServiceImpl implements AdminApprovalService {

    @Autowired
    private AdminApprovalRepository adminApprovalRepository;

    @Autowired
    private AdminLoginRepository adminLoginRepository;

    @Autowired
    private AdminHomestayRepository adminHomestayRepository;

    @Autowired
    private EmailSender emailSender;

    @Override
    public ApprovalHistory agree(AdminApprovalRequest adminApprovalRequest) {
        ApprovalHistory approvalHistory = new ApprovalHistory();
        approvalHistory.setAdmin(adminLoginRepository.findById(adminApprovalRequest.getAdminId()).orElse(null));
        approvalHistory.setDesc(adminApprovalRequest.getDesc());
        Homestay homestay=  adminHomestayRepository.findById(adminApprovalRequest.getHomestayId()).orElse(null);
        homestay.setStatus(Status.HOAT_DONG);
        adminHomestayRepository.save(homestay);
        approvalHistory.setHomestay(adminHomestayRepository.findById(adminApprovalRequest.getHomestayId()).orElse(null));
        ApprovalHistory approvalHistory1 = adminApprovalRepository.save(approvalHistory);

        Email email = new Email();
        email.setToEmail(new String[]{homestay.getOwnerHomestay().getEmail()});
        email.setSubject("Yêu cầu phê duyệt đã được chấp nhận");
        email.setTitleEmail("Chúc mừng " + homestay.getOwnerHomestay().getName());
        email.setBody("Phòng của bạn đã được phê duyệt");
        emailSender.sendEmail(email.getToEmail(), email.getSubject(), email.getTitleEmail(), email.getBody());
        return approvalHistory1;
    }

    @Override
    public ApprovalHistory disAgree(AdminApprovalRequest adminApprovalRequest) {
        ApprovalHistory approvalHistory = new ApprovalHistory();
        approvalHistory.setAdmin(adminLoginRepository.findById(adminApprovalRequest.getAdminId()).orElse(null));
        approvalHistory.setDesc(adminApprovalRequest.getDesc());
        Homestay homestay=  adminHomestayRepository.findById(adminApprovalRequest.getHomestayId()).orElse(null);
        homestay.setStatus(Status.KHONG_DUYET);
        adminHomestayRepository.save(homestay);
        approvalHistory.setHomestay(adminHomestayRepository.findById(adminApprovalRequest.getHomestayId()).orElse(null));
        ApprovalHistory approvalHistory1 = adminApprovalRepository.save(approvalHistory);

        Email email = new Email();
        email.setToEmail(new String[]{homestay.getOwnerHomestay().getEmail()});
        email.setSubject("Yêu cầu phê duyệt đã bị từ chối");
        email.setTitleEmail("Xin lỗi" + homestay.getOwnerHomestay().getName());
        email.setBody("Phòng của bạn đã bị từ chối vì lý do: "+approvalHistory1.getDesc());
        emailSender.sendEmail(email.getToEmail(), email.getSubject(), email.getTitleEmail(), email.getBody());
        return approvalHistory1;
    }
}
