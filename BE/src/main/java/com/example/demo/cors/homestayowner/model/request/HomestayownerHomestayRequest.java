package com.example.demo.cors.homestayowner.model.request;

import com.example.demo.cors.common.base.PageableRequest;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HomestayownerHomestayRequest extends PageableRequest{

      private String name;

      private Long startDate;

      private Long endDate;

      private String desc;

      private Double acreage;

      private String timeCheckIn;

      private String timeCheckOut;

      private Integer roomNumber;

      private BigDecimal price;

      private Integer numberPerson;

      private String address;

      private Double CancellationPolicy;

      private String ownerHomestay;

      private String promotion;

      private String status;

}
