package com.example.demo.cors.homestayowner.model.request;

import com.example.demo.entities.ConvenientHomestay;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HomestayOwnerDetailHomestayRequest {

    private String idHomestay;

    private String idConvenientHomestay;

}
