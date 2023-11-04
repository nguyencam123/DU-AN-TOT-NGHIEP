package com.example.demo.cors.admin.services.impl;

import com.example.demo.cors.admin.repository.AdminHomestayRepository;
import com.example.demo.cors.admin.services.AdminApproveHomestayService;
import com.example.demo.entities.Homestay;
import com.example.demo.infrastructure.configemail.EmailSender;
import com.example.demo.infrastructure.contant.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminApproveHomestayServiceImpl implements AdminApproveHomestayService {

    @Autowired
    private AdminHomestayRepository adminHomestayRepository;

    @Autowired
    private EmailSender emailSender;

    @Override
    public Homestay adminApproveHomestay(String id) {
        Homestay homestay = adminHomestayRepository.findById(id).get();
        homestay.setStatus(Status.HOAT_DONG);
        adminHomestayRepository.save(homestay);

        String[] emailOwner = {homestay.getOwnerHomestay().getEmail()};
        emailSender.sendEmail(emailOwner, "Yêu cầu phê duyệt", "Chúc mừng", "Phòng của bạn đã được phê duyệt");
        return homestay;
    }

}
