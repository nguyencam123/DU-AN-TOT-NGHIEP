package com.example.demo.cors.homestayowner.model.request;

import com.example.demo.infrastructure.contant.TypePromotion;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HomestayOwnerPromotionRequest {

    private String name;

    private Long StartDate;

    private Long endDate;

    private TypePromotion type;

    private Double value;

    private String owner;

    private List<String> homestay;

}
