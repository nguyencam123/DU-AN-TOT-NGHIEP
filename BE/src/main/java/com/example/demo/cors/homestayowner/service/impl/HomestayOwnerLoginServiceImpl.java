package com.example.demo.cors.homestayowner.service.impl;

import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerLoginReponse;
import com.example.demo.cors.homestayowner.model.request.HomestayownerLoginRequest;
import com.example.demo.cors.homestayowner.repository.HomestayOwnerOwnerHomestayRepository;
import com.example.demo.cors.homestayowner.service.HomestayOwnerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class HomestayOwnerLoginServiceImpl implements HomestayOwnerLoginService {

    @Autowired
    private HomestayOwnerOwnerHomestayRepository homestayownerOwnerHomestayRepository;

    @Override
    public HomestayOwnerLoginReponse login(@RequestBody HomestayownerLoginRequest homestayownerLoginRequest) {
        return homestayownerOwnerHomestayRepository.getLoginOwnerHomestay(homestayownerLoginRequest);
    }

}
