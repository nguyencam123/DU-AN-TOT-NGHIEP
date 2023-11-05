package com.example.demo.cors.admin.services.impl;

import com.example.demo.cors.admin.model.request.AdminApprovalRequest;
import com.example.demo.cors.admin.repository.AdminApprovalRepository;
import com.example.demo.cors.admin.repository.AdminHomestayRepository;
import com.example.demo.cors.admin.repository.AdminLoginRepository;
import com.example.demo.cors.admin.services.AdminApprovalService;
import com.example.demo.entities.ApprovalHistory;
import com.example.demo.entities.Homestay;
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
        return approvalHistory1;
    }
}
