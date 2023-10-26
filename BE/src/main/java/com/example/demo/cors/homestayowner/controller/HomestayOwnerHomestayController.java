package com.example.demo.cors.homestayowner.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.homestayowner.model.conventer.HomestayOwnerHomestayConventer;
import com.example.demo.cors.homestayowner.model.request.HomestayownerHomestayRequest;
import com.example.demo.cors.homestayowner.service.HomestayOwnerHomestayService;
import com.example.demo.cors.homestayowner.service.HomestayOwnerImgHomestayService;
import com.example.demo.entities.ImgHomestay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v2/homestay/")
public class HomestayOwnerHomestayController {

    @Autowired
    private HomestayOwnerHomestayService homestayownerHomestayService;

    @Autowired
    private HomestayOwnerImgHomestayService homestayOwnerImgHomestayService;

    @Autowired
    private HomestayOwnerHomestayConventer conventer;

    @GetMapping("get-homestay")
    public ResponseObject getPageHomestayownerHomestay(HomestayownerHomestayRequest homestayownerHomestayRequest) {
        return new ResponseObject(homestayownerHomestayService.getAllPageable(homestayownerHomestayRequest));
    }

    @PostMapping("add-homestay")
    public ResponseObject addHomestay(@RequestBody HomestayownerHomestayRequest homestayOwnerAddHomestayRequest){
        return new ResponseObject(homestayownerHomestayService.addHomestay(homestayOwnerAddHomestayRequest));
    }

    @GetMapping("get-imghomestay")
    public ResponseObject getPageHomestayownerHomestay(@RequestParam("id") String id) {
        return new ResponseObject(homestayOwnerImgHomestayService.getImgHomestayByHomestayId(id));
    }

    @PostMapping("addImage")
    public ResponseObject addImagesHomestay(@RequestParam("id") String id, @RequestParam("images") List<MultipartFile> images) throws IOException{
        return new ResponseObject(homestayownerHomestayService.addImgHomestay(id,images));
    }

    @PostMapping("add-homestays")
    public ResponseObject addhomestays(@RequestParam("homestay") String homestay,@RequestParam("image") List<MultipartFile> images) throws IOException{
         HomestayownerHomestayRequest request=conventer.convert(homestay);
         return new ResponseObject(homestayownerHomestayService.addHomestays(request,images));
    }

    @PutMapping("update-homestays")
    public ResponseObject addhomestays(@RequestParam("id") String id,@RequestParam("homestay") String homestay,@RequestParam("image") List<MultipartFile> images) throws IOException{
        HomestayownerHomestayRequest request=conventer.convert(homestay);
        return new ResponseObject(homestayownerHomestayService.updateHomestays(id,request,images));
    }

}
