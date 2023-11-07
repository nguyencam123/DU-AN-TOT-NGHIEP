package com.example.demo.cors.homestayowner.model.reponse;


import com.example.demo.entities.*;
import com.example.demo.infrastructure.contant.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class HomestayOwnerHomestayDetailReponse {
    private String id;
    private Promotion promotion;
    private String address;
    private String timeCheckIn;
    private String timeCheckOut;
    private OwnerHomestay ownerHomestay;
    private String name;
    private Double point;
    private Status status;
    private Long startDate;
    private Long endDate;
    private String desc;
    private BigDecimal price;
    private Integer numberPerson;
    private List<ImgHomestay> images;
    private List<DetailHomestay> detailHomestays;
    private List<ConvenientHomestay> convenientHomestays;
//    private List<String> nameConvenientHomestay;

    public HomestayOwnerHomestayDetailReponse(Homestay homestay) {
        this.id = homestay.getId();
        this.promotion = homestay.getPromotion();
        this.address = homestay.getAddress();
        this.timeCheckIn = homestay.getTimeCheckIn();
        this.timeCheckOut = homestay.getTimeCheckOut();
        this.ownerHomestay = homestay.getOwnerHomestay();
        this.name = homestay.getName();
        this.point = homestay.getPoint();
        this.status = homestay.getStatus();
        this.startDate = homestay.getStartDate();
        this.endDate = homestay.getEndDate();
        this.desc = homestay.getDesc();
        this.price = homestay.getPrice();
        this.numberPerson = homestay.getNumberPerson();
        this.images = homestay.getImages();
        this.detailHomestays = homestay.getDetailHomestays();
//        if (this.idConvenientHomestay == null) {
//            this.idConvenientHomestay = new ArrayList<>();
//        }
//        if (this.nameConvenientHomestay == null) {
//            this.nameConvenientHomestay = new ArrayList<>();
//        }
//        if (homestay.getDetailHomestays() != null) {
//            this.detailHomestays = homestay.getDetailHomestays();
//            for (DetailHomestay detail : homestay.getDetailHomestays()) {
//                ConvenientHomestay convenientHomestay = detail.getConvenientHomestay();
//                if (convenientHomestay != null) {
//                    this.idConvenientHomestay.add(convenientHomestay.getId());
//                    this.nameConvenientHomestay.add(convenientHomestay.getName());
//                } else {
//                    this.idConvenientHomestay.add("null");
//                    this.nameConvenientHomestay.add("null");
//                }
        if (this.convenientHomestays == null) {
            this.convenientHomestays = new ArrayList<>();
            if (homestay.getDetailHomestays() != null) {
                this.detailHomestays = homestay.getDetailHomestays();
                for (DetailHomestay detail : homestay.getDetailHomestays()) {
                    ConvenientHomestay convenientHomestay = detail.getConvenientHomestay();
                    this.convenientHomestays.add(convenientHomestay);

                }
            }
        }
    }
}