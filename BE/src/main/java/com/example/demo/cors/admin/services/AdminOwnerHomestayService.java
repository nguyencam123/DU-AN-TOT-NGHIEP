package com.example.demo.cors.admin.services;


import com.example.demo.cors.admin.model.request.AdminOwnerHomestayAppRequest;
import com.example.demo.cors.admin.model.request.AdminOwnerHomestayRequest;
import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.entities.Homestay;
import com.example.demo.entities.OwnerHomestay;

public interface AdminOwnerHomestayService {

    PageableObject<OwnerHomestay> getAllOwner(AdminOwnerHomestayRequest request);

    OwnerHomestay adminApprovalOwnerHomestay(AdminOwnerHomestayAppRequest request);

    OwnerHomestay adminRefuseOwnerHomestay(AdminOwnerHomestayAppRequest request);
}
