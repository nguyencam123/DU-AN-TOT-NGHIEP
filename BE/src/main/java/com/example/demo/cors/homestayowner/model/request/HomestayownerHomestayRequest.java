package com.example.demo.cors.homestayowner.model.request;

import com.example.demo.cors.common.base.PageableRequest;
import com.example.demo.entities.ImgHomestay;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HomestayownerHomestayRequest extends PageableRequest {

      private String name;

      private Long startDate;

      private String address;

      private String region;

      private String province;

      private List<String> img;

      private String statusServicePack;

}
