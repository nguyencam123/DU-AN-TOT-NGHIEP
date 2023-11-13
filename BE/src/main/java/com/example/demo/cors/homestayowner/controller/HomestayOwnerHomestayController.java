package com.example.demo.cors.homestayowner.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.homestayowner.model.conventer.HomestayOwnerHomestayConventer;
import com.example.demo.cors.homestayowner.model.request.HomestayownerHomestayRequest;
import com.example.demo.cors.homestayowner.service.HomestayOwnerHomestayService;
import com.example.demo.cors.homestayowner.service.HomestayOwnerImgHomestayService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v2/homestay/")
@RequiredArgsConstructor
public class HomestayOwnerHomestayController {

    @Autowired
    private HomestayOwnerHomestayService homestayownerHomestayService;

    @Autowired
    private HomestayOwnerImgHomestayService homestayOwnerImgHomestayService;

    @Autowired
    private HomestayOwnerHomestayConventer conventer;

    @GetMapping("get-homestay-by-id")
    public ResponseObject getAllHomestayownerHomestay(@RequestParam("id") String id,HomestayownerHomestayRequest homestayownerHomestayRequest) {
        return new ResponseObject(homestayownerHomestayService.getPageHomestay(id,homestayownerHomestayRequest));
    }

    @GetMapping("get-imghomestay")
    public ResponseObject getPageHomestayownerHomestay(@RequestParam("id") String id) {
        return new ResponseObject(homestayOwnerImgHomestayService.getImgHomestayByHomestayId(id));
    }

    @PostMapping("add-homestay")
    public ResponseObject addhomestays(@RequestParam("homestay") String homestay,@RequestParam("image") List<MultipartFile> images,@RequestParam("convenient") List<String> idConvenientHomestay) throws IOException{
        HomestayownerHomestayRequest request=conventer.convert(homestay);
        return new ResponseObject(homestayownerHomestayService.addHomestay(request,images,idConvenientHomestay));
    }

    @PutMapping("update-homestays")
    public ResponseObject updatehomestays(@RequestParam("id") String id,@RequestParam("homestay") String homestay,@RequestParam("image") List<MultipartFile> images,@RequestParam("convenient") List<String> idConvenientHomestay) throws IOException{
        HomestayownerHomestayRequest request=conventer.convert(homestay);
        return new ResponseObject(homestayownerHomestayService.updateHomestays(id,request,images,idConvenientHomestay));
    }

    @PutMapping("delete-homestays")
    public ResponseObject updatehomestays(@RequestParam("id") String id){
        return new ResponseObject(homestayownerHomestayService.deleteHomestays(id));
    }

    @PutMapping("status-homestay")
    public ResponseObject updateStatushomestays(@RequestParam("id") String id){
        return new ResponseObject(homestayownerHomestayService.updateStatusHomestay(id));
    }

}
