package com.example.demo.cors.admin.services;

import com.example.demo.cors.admin.model.request.AdminApprovalRequest;
import com.example.demo.entities.ApprovalHistory;

public interface AdminApprovalService {

    ApprovalHistory agree(AdminApprovalRequest adminApprovalRequest);

    ApprovalHistory disAgree(AdminApprovalRequest adminApprovalRequest);

}
