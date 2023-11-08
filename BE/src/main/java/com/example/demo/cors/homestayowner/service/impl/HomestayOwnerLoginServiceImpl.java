package com.example.demo.cors.homestayowner.service.impl;

import com.cloudinary.Cloudinary;
import com.example.demo.cors.homestayowner.model.reponse.loginreponse.HomestayOwnerAuthenticationReponse;
import com.example.demo.cors.homestayowner.model.request.loginrequest.HomestayOwnerOwnerHomestayRequest;
import com.example.demo.cors.homestayowner.model.request.loginrequest.HomestayOwnerPasswordRequest;
import com.example.demo.cors.homestayowner.model.request.loginrequest.HomestayOwnerUsenamePasswordRequest;
import com.example.demo.cors.homestayowner.repository.HomestayOwnerOwnerHomestayRepository;
import com.example.demo.cors.homestayowner.service.HomestayOwnerLoginService;
import com.example.demo.entities.Homestay;
import com.example.demo.entities.OwnerHomestay;
import com.example.demo.infrastructure.configemail.Email;
import com.example.demo.infrastructure.configemail.EmailSender;
import com.example.demo.infrastructure.contant.Status;
import com.example.demo.infrastructure.exception.rest.RestApiException;
import com.example.demo.infrastructure.security.token.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HomestayOwnerLoginServiceImpl implements HomestayOwnerLoginService {

    @Autowired
    private HomestayOwnerOwnerHomestayRepository homestayownerOwnerHomestayRepository;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public HomestayOwnerAuthenticationReponse register(HomestayOwnerOwnerHomestayRequest request) {
        if (isNullOrEmpty(request.getUsername())) {
            throw new RestApiException("Username cannot be empty");
        }
        if (isNullOrEmpty(request.getPhoneNumber())) {
            throw new RestApiException("Phone number cannot be empty");
        }
        if (isNullOrEmpty(request.getEmail())) {
            throw new RestApiException("Email cannot be empty");
        }
        if (isNullOrEmpty(request.getPassword())) {
            throw new RestApiException("Password cannot be empty");
        }
        OwnerHomestay ownerHomestay=new OwnerHomestay();
        Random random = new Random();
        int number = random.nextInt(1000);
        String code=String.format("G%04d",number);
        ownerHomestay.setCode(code);
        if (homestayownerOwnerHomestayRepository.existsByUsername(request.getUsername())) {
            throw new RestApiException("Username is already in use");
        }
        if (homestayownerOwnerHomestayRepository.existsByEmail(request.getEmail())) {
            throw new RestApiException("Email is already in use");
        }
        if (homestayownerOwnerHomestayRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new RestApiException("PhoneNumber is already in use");
        }
        String phoneNumber = request.getPhoneNumber();
        if (!isValidVietnamesePhoneNumber(phoneNumber)) {
            throw new RestApiException("Invalid Vietnamese phone number format");
        }
        ownerHomestay.setPhoneNumber(request.getPhoneNumber());
        ownerHomestay.setEmail(request.getEmail());
        ownerHomestay.setUsername(request.getUsername());
        ownerHomestay.setPassword(passwordEncoder.encode(request.getPassword()));
        ownerHomestay.setStatus(Status.HOAT_DONG);
        homestayownerOwnerHomestayRepository.save(ownerHomestay);
        var jwtServices=jwtService.generateToken(ownerHomestay);
        return HomestayOwnerAuthenticationReponse.builder()
                .code(ownerHomestay.getCode())
                .id(ownerHomestay.getId())
                .name(ownerHomestay.getName())
                .birthday(ownerHomestay.getBirthday())
                .gender(ownerHomestay.getGender())
                .address(ownerHomestay.getAddress())
                .phoneNumber(ownerHomestay.getPhoneNumber())
                .email(ownerHomestay.getEmail())
                .username(ownerHomestay.getUsername())
                .status(ownerHomestay.getStatus())
                .build();
    }

    @Override
    public HomestayOwnerAuthenticationReponse authenticate(HomestayOwnerUsenamePasswordRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var ownerHomestay=homestayownerOwnerHomestayRepository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken=jwtService.generateToken(ownerHomestay);
        return HomestayOwnerAuthenticationReponse.builder().
                token(jwtToken)
                .id(ownerHomestay.getId())
                .code(ownerHomestay.getCode())
                .name(ownerHomestay.getName())
                .birthday(ownerHomestay.getBirthday())
                .gender(ownerHomestay.getGender())
                .address(ownerHomestay.getAddress())
                .phoneNumber(ownerHomestay.getPhoneNumber())
                .email(ownerHomestay.getEmail())
                .username(ownerHomestay.getUsername())
                .status(ownerHomestay.getStatus())
                .build();
    }

    @Override
    public HomestayOwnerAuthenticationReponse changePassword(HomestayOwnerPasswordRequest request, Principal connecteUser) {
        var ownerHomestay=(OwnerHomestay) ((UsernamePasswordAuthenticationToken) connecteUser).getPrincipal();
        if(!passwordEncoder.matches(request.getCurrentPassword(), ownerHomestay.getPassword())){
            throw new IllegalStateException("Wrong password");
        };
        if(!request.getNewPassword().equals(request.getConfirmationPassword())){
            throw new IllegalStateException("password aren't the same");
        }
        ownerHomestay.setPassword(passwordEncoder.encode(request.getNewPassword()));
        homestayownerOwnerHomestayRepository.save(ownerHomestay);
        return HomestayOwnerAuthenticationReponse.builder()
                .id(ownerHomestay.getId())
                .code(ownerHomestay.getCode())
                .name(ownerHomestay.getName())
                .birthday(ownerHomestay.getBirthday())
                .gender(ownerHomestay.getGender())
                .address(ownerHomestay.getAddress())
                .phoneNumber(ownerHomestay.getPhoneNumber())
                .email(ownerHomestay.getEmail())
                .username(ownerHomestay.getUsername())
                .status(ownerHomestay.getStatus())
                .build();
    }

    @Override
    public HomestayOwnerAuthenticationReponse updateInformationOwner(String idOwner, HomestayOwnerOwnerHomestayRequest request, MultipartFile multipartFile) throws IOException {
        checkNull(isNullOrEmpty(request.getUsername()), isNullOrEmpty(request.getName()), request.getBirthday(), isNullOrEmpty(request.getAddress()), isNullOrEmpty(request.getPhoneNumber()), isNullOrEmpty(request.getEmail()), request);
        OwnerHomestay ownerHomestay=homestayownerOwnerHomestayRepository.findById(idOwner).orElse(null);
        ownerHomestay.setName(request.getName());
        ownerHomestay.setBirthday(request.getBirthday());
        ownerHomestay.setGender(request.getGender());
        ownerHomestay.setAddress(request.getAddress());
        ownerHomestay.setPhoneNumber(request.getPhoneNumber());
        ownerHomestay.setEmail(request.getEmail());
        ownerHomestay.setUsername(request.getUsername());
        if (multipartFile==null){
            ownerHomestay.setAvatarUrl(null);
        }else {
            ownerHomestay.setAvatarUrl(cloudinary.uploader()
                    .upload(multipartFile.getBytes(),
                            Map.of("id", UUID.randomUUID().toString()))
                    .get("url")
                    .toString());
        }
        ownerHomestay.setStatus(Status.HOAT_DONG);
        homestayownerOwnerHomestayRepository.save(ownerHomestay);
        var jwtServices=jwtService.generateToken(ownerHomestay);
        return HomestayOwnerAuthenticationReponse.builder()
                .code(ownerHomestay.getCode())
                .id(ownerHomestay.getId())
                .name(ownerHomestay.getName())
                .birthday(ownerHomestay.getBirthday())
                .gender(ownerHomestay.getGender())
                .address(ownerHomestay.getAddress())
                .phoneNumber(ownerHomestay.getPhoneNumber())
                .email(ownerHomestay.getEmail())
                .avataUrl(ownerHomestay.getAvatarUrl())
                .username(ownerHomestay.getUsername())
                .status(ownerHomestay.getStatus())
                .build();
    }

    public void checkNull(boolean nullOrEmpty, boolean nullOrEmpty2, Long birthday, boolean nullOrEmpty3, boolean nullOrEmpty4, boolean nullOrEmpty5, HomestayOwnerOwnerHomestayRequest request) {
        if (nullOrEmpty) {
            throw new RestApiException("Username cannot be empty");
        }
        if (nullOrEmpty2) {
            throw new RestApiException("Name cannot be empty");
        }
        if (birthday == null) {
            throw new RestApiException("Birthday cannot be empty");
        }
        if (nullOrEmpty3) {
            throw new RestApiException("Address cannot be empty");
        }
        if (nullOrEmpty4) {
            throw new RestApiException("Phone number cannot be empty");
        }
        if (nullOrEmpty5) {
            throw new RestApiException("Email cannot be empty");
        }
    }
    public String createResetPasswordToken(String username) {
        OwnerHomestay ownerHomestay = homestayownerOwnerHomestayRepository.findByUsername(username).orElse(null);
        if (ownerHomestay != null) {
            String resetPasswordToken = generateResetPasswordToken();
            ownerHomestay.setPassword(resetPasswordToken);
            homestayownerOwnerHomestayRepository.save(ownerHomestay);
            return resetPasswordToken;
        }
        return null;
    }

    private String generateResetPasswordToken() {
        return UUID.randomUUID().toString();
    }

    public void sendResetPasswordEmail(String username, String resetPasswordToken) {
        OwnerHomestay owner = homestayownerOwnerHomestayRepository.findByUsername(username).get();
        String resetPasswordLink = "http://localhost:8080/api/v2/reset-password?token=" + resetPasswordToken;
        String emailBody = "Bạn đã yêu cầu đặt lại mật khẩu. Vui lòng nhấp vào liên kết sau để thực hiện đặt lại mật khẩu: " + resetPasswordLink;

        Email email = new Email();
        email.setToEmail(new String[]{owner.getEmail()});
        email.setSubject("Yêu cầu đặt lại mật khẩu");
        email.setTitleEmail("Đặt lại mật khẩu");
        email.setBody(emailBody);
        emailSender.sendEmail(email.getToEmail(), email.getSubject(), email.getTitleEmail(), email.getBody());
    }

    private boolean isValidVietnamesePhoneNumber(String phoneNumber) {
        String regex = "^(03|05|07|08|09)\\d{8}$";
        return phoneNumber.matches(regex);
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
