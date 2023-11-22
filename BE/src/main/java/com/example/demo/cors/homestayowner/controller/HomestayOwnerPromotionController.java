package com.example.demo.cors.homestayowner.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerPromotionRequest;
import com.example.demo.cors.homestayowner.service.HomestayOwnerPromotionService;
import com.example.demo.entities.Promotion;
import com.example.demo.infrastructure.exception.rest.RestApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v2/promotion")
@PreAuthorize("hasRole('OWNER')")
public class HomestayOwnerPromotionController {

    @Autowired
    private HomestayOwnerPromotionService homestayOwnerPromotionService;

    @GetMapping()
    @PreAuthorize("hasAuthority('owner:read')")
    public ResponseObject getPromotion(@RequestParam("idOwner") String idOwner) {
        return new ResponseObject(homestayOwnerPromotionService.getPromotion(idOwner));
    }

    @PostMapping("/add-promotion")
    @PreAuthorize("hasAuthority('owner:create')")
    public ResponseObject addPromotion(@RequestBody HomestayOwnerPromotionRequest request) throws IOException {
        try {
            Promotion promotion = homestayOwnerPromotionService.addPromotion(request);
            ResponseObject responseObject = new ResponseObject(promotion);
            responseObject.setMessage("Thành công");
            return responseObject;
        } catch (RestApiException ex) {
            ResponseObject responseObject = new ResponseObject(null);
            responseObject.setMessage("Không thành công: " + ex.getMessage());
            return responseObject;
        }
    }

    @PutMapping("/update-promotion")
    @PreAuthorize("hasAuthority('owner:update')")
    public ResponseObject updatePromotion(@RequestParam("idPromotion") String id, @RequestBody HomestayOwnerPromotionRequest request) throws IOException {
        try {
            Promotion promotion = homestayOwnerPromotionService.updatePromotion(id, request);
            ResponseObject responseObject = new ResponseObject(promotion);
            responseObject.setMessage("Thành công");
            return responseObject;
        } catch (RestApiException ex) {
            ResponseObject responseObject = new ResponseObject(null);
            responseObject.setMessage("Không thành công: " + ex.getMessage());
            return responseObject;
        }
    }

}
