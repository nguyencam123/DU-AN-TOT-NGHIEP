package com.example.demo.cors.homestayowner.model.reponse;

import org.springframework.beans.factory.annotation.Value;

public interface HomestayOwnerHomestayReponse {

    @Value("#{target.name}")
    String getName();

    @Value("#{target.address}")
    String getAddress();

    @Value("#{target.servicePack}")
    String getServicePack();

    @Value("#{target.statusDate}")
    String getStatusDate();

    @Value("#{target.statusHomestay}")
    String getStatusHomestay();

    @Value("#{target.statusServicePack}")
    String getStatusServicePack();

}
