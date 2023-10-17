package com.example.demo.cors.homestayowner.service.impl;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerHomestayReponse;
import com.example.demo.cors.homestayowner.model.request.HomestayownerHomestayRequest;
import com.example.demo.cors.homestayowner.repository.HomestayOwnerHomestayRepository;
import com.example.demo.cors.homestayowner.service.HomestayOwnerHomestayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HomestayOwnerHomestayServiceImpl implements HomestayOwnerHomestayService {

    @Autowired
    private HomestayOwnerHomestayRepository homestayownerHomestayRepository;

    @Override
    public PageableObject<HomestayOwnerHomestayReponse> getAllPageable(HomestayownerHomestayRequest homestayownerHomestayRequest) {
        Pageable pageable = PageRequest.of(homestayownerHomestayRequest.getPage(),homestayownerHomestayRequest.getSize());
        Page<HomestayOwnerHomestayReponse> res=homestayownerHomestayRepository.getALlHomestayPage(pageable);
        return new PageableObject<>(res);
    }

}
