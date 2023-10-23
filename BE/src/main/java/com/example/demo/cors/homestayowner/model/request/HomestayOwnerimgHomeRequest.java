package com.example.demo.cors.homestayowner.model.request;

import com.example.demo.entities.ImgHomestay;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HomestayOwnerimgHomeRequest {
    private String homestay;

    private List<MultipartFile> images;
}
