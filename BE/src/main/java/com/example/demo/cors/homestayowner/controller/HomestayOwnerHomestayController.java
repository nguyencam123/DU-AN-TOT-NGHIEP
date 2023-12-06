package com.example.demo.cors.homestayowner.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.homestayowner.model.conventer.HomestayOwnerHomestayConventer;
import com.example.demo.cors.homestayowner.model.request.HomestayownerHomestayRequest;
import com.example.demo.cors.homestayowner.service.HomestayOwnerHomestayService;
import com.example.demo.cors.homestayowner.service.HomestayOwnerImgHomestayService;
import com.example.demo.entities.Homestay;
import com.example.demo.infrastructure.exception.rest.RestApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("get-homestay-by-id")
    @PreAuthorize("hasAuthority('owner:read')")
    public ResponseObject getAllHomestayownerHomestay(@RequestParam("id") String id, HomestayownerHomestayRequest homestayownerHomestayRequest) {
        return new ResponseObject(homestayownerHomestayService.getPageHomestay(id, homestayownerHomestayRequest));
    }

    @GetMapping("get-imghomestay")
    public ResponseObject getPageHomestayownerHomestay(@RequestParam("id") String id) {
        return new ResponseObject(homestayOwnerImgHomestayService.getImgHomestayByHomestayId(id));
    }

    @PostMapping("add-homestay")
    @PreAuthorize("hasAuthority('owner:create')")
    public ResponseObject addHomestays(@RequestPart String homestay,
                                       @RequestParam("image") List<MultipartFile> images,
                                       @RequestParam("convenient") List<String> idConvenientHomestay) throws IOException {
        try {
            HomestayownerHomestayRequest request = conventer.convert(homestay);
            Homestay homestay1 = homestayownerHomestayService.addHomestay(request, images, idConvenientHomestay);
            ResponseObject responseObject = new ResponseObject(homestay1);
            responseObject.setMessage("Thành công");
            return responseObject;
        } catch (RestApiException ex) {
            ResponseObject responseObject = new ResponseObject(null);
            responseObject.setMessage("Không thành công: " + ex.getMessage());
            return responseObject;
        }
    }

    @PutMapping("update-homestays")
    @PreAuthorize("hasAuthority('owner:update')")
    public ResponseObject updatehomestays(@RequestParam("id") String id,
                                          @RequestParam("homestay") String homestay,
                                          @RequestParam("image") List<MultipartFile> images,
                                          @RequestParam("convenient") List<String> idConvenientHomestay) throws IOException {
        try {
            HomestayownerHomestayRequest request = conventer.convert(homestay);
            Homestay homestay1 = homestayownerHomestayService.updateHomestays(id, request, images, idConvenientHomestay);
            ResponseObject responseObject = new ResponseObject(homestay1);
            responseObject.setMessage("Thành công");
            return responseObject;
        } catch (RestApiException ex) {
            ResponseObject responseObject = new ResponseObject(null);
            responseObject.setMessage("Không thành công: " + ex.getMessage());
            return responseObject;
        }
    }

    @PutMapping("delete-homestays")
    @PreAuthorize("hasAuthority('owner:update')")
    public ResponseObject updatehomestays(@RequestParam("id") String id) {
        return new ResponseObject(homestayownerHomestayService.deleteHomestays(id));
    }

    @PutMapping("status-homestay")
    @PreAuthorize("hasAuthority('owner:update')")
    public ResponseObject updateStatushomestays(@RequestParam("id") String id) {
        return new ResponseObject(homestayownerHomestayService.updateStatusHomestay(id));
    }

    @PutMapping("update-promotion-homestay")
    @PreAuthorize("hasAuthority('owner:update')")
    public ResponseObject updatePromotionhomestays(@RequestParam("id") String id, @RequestBody HomestayownerHomestayRequest request) {
        return new ResponseObject(homestayownerHomestayService.updateHomestayPromition(id, request));
    }

}
