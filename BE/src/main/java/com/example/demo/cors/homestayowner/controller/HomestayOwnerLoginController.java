package com.example.demo.cors.homestayowner.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.homestayowner.model.request.loginrequest.HomestayOwnerOwnerHomestayRequest;
import com.example.demo.cors.homestayowner.model.request.loginrequest.HomestayOwnerUsenamePasswordRequest;
import com.example.demo.cors.homestayowner.model.request.loginrequest.HomestayownerLoginRequest;
import com.example.demo.cors.homestayowner.service.HomestayOwnerForgetPassService;
import com.example.demo.cors.homestayowner.service.HomestayOwnerLoginService;
import com.example.demo.entities.Homestay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v2/login")
public class HomestayOwnerLoginController {

    @Autowired
    private HomestayOwnerLoginService homestayownerLoginService;

    @Autowired
    private HomestayOwnerForgetPassService createResetPasswordToken;


    @PostMapping("/authenticate")
    public ResponseObject authenticate(@RequestBody HomestayOwnerUsenamePasswordRequest request){
        return new ResponseObject(homestayownerLoginService.authenticate(request));
    }

    @PostMapping("/registers")
    public ResponseObject registers(@RequestBody HomestayOwnerOwnerHomestayRequest request){
        return new ResponseObject(homestayownerLoginService.register(request));
    }

    @PostMapping("/reset-password")
    public ResponseObject resetPassword(@RequestParam("username") String username) {
        String resetPasswordToken = createResetPasswordToken.createResetPasswordToken(username);

        if (resetPasswordToken != null) {
            createResetPasswordToken.sendResetPasswordEmail(username, resetPasswordToken);
            return new ResponseObject("Yêu cầu đặt lại mật khẩu đã được gửi đến địa chỉ email của bạn.");
        } else {
            return new ResponseObject("Không tìm thấy tài khoản với tên đăng nhập này.");
        }
    }

    @PostMapping("/reset-passwords")
    public ResponseObject resetPasswordWithToken(@RequestParam("token") String token, @RequestParam("newPass") String newPassword) {
        boolean isTokenValid = createResetPasswordToken.isResetPasswordTokenValid(token);

        if (isTokenValid) {
            createResetPasswordToken.resetPasswordWithToken(token, newPassword);
            return new ResponseObject("Mật khẩu đã được đặt lại thành công.");
        } else {
            return new ResponseObject("Token không hợp lệ hoặc đã hết hạn.");
        }
    }

    @PostMapping("/confirm-email")
    public ResponseObject confirmEmail(@RequestParam("id") String id) {
        homestayownerLoginService.confirmEmail(id);
        return new ResponseObject("Email xác nhận thành công");
    }

}
