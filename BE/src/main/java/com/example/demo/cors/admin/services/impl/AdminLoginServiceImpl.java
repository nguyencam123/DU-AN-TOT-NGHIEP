package com.example.demo.cors.admin.services.impl;

import com.cloudinary.Cloudinary;
import com.example.demo.cors.admin.model.request.AdminLoginRequest;
import com.example.demo.cors.admin.model.request.AdminPassRequest;
import com.example.demo.cors.admin.model.request.AdminRequest;
import com.example.demo.cors.admin.model.request.AdminUserPasswordRequest;
import com.example.demo.cors.admin.model.response.AdminAuthenticationReponse;
import com.example.demo.cors.admin.model.response.AdminLoginResponse;
import com.example.demo.cors.admin.repository.AdminLoginRepository;
import com.example.demo.cors.admin.repository.AdminTokenRepository;
import com.example.demo.cors.admin.services.AdminLoginService;
import com.example.demo.entities.Admin;
import com.example.demo.entities.Token;
import com.example.demo.entities.User;
import com.example.demo.infrastructure.contant.Status;
import com.example.demo.infrastructure.contant.TypeToken;
import com.example.demo.infrastructure.contant.role.RoleAdmin;
import com.example.demo.infrastructure.exception.rest.RestApiException;
import com.example.demo.infrastructure.security.token.JwtService;
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
public class AdminLoginServiceImpl implements AdminLoginService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AdminTokenRepository adminTokenRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AdminLoginRepository adminLoginRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public AdminLoginResponse getAdLogin(AdminLoginRequest adminLoginRequest) {
        return adminLoginRepository.getLogin(adminLoginRequest);
    }

    @Override
    public AdminAuthenticationReponse register(AdminRequest request) {
        if (isNullOrEmpty(request.getUsername())) {
            throw new RestApiException("Username cannot be empty");
        }
        if (isNullOrEmpty(request.getName())) {
            throw new RestApiException("Name cannot be empty");
        }
        if (request.getBirthday() == null) {
            throw new RestApiException("Birthday cannot be empty");
        }
        if (isNullOrEmpty(request.getAddress())) {
            throw new RestApiException("Address cannot be empty");
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
        Admin admin = new Admin();
        Random random = new Random();
        int number = random.nextInt(1000);
        String code = String.format("G%04d", number);
        admin.setCode(code);
        if (adminLoginRepository.existsByUsername(request.getUsername())) {
            throw new RestApiException("Username is already in use");
        }
        if (adminLoginRepository.existsByEmail(request.getEmail())) {
            throw new RestApiException("Email is already in use");
        }
        admin.setName(request.getName());
        admin.setBirthday(request.getBirthday());
        admin.setGender(request.getGender());
        admin.setAddress(request.getAddress());
        admin.setPhoneNumber(request.getPhoneNumber());
        admin.setEmail(request.getEmail());
        admin.setUsername(request.getUsername());
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        admin.setStatus(Status.HOAT_DONG);
        admin.setRole(RoleAdmin.ADMIN);
        Admin admin1=adminLoginRepository.save(admin);
        var jwtServices = jwtService.generateToken(admin);
        saveUserToken(admin1, jwtServices);

        return AdminAuthenticationReponse.builder().
                token(jwtServices)
                .id(admin.getId())
                .code(admin.getCode())
                .name(admin.getName())
                .birthday(admin.getBirthday())
                .gender(admin.getGender())
                .address(admin.getAddress())
                .phoneNumber(admin.getPhoneNumber())
                .email(admin.getEmail())
                .username(admin.getUsername())
                .status(admin.getStatus())
                .roleAdmin(admin.getRole())
                .build();
    }

    @Override
    public AdminAuthenticationReponse authenticate(AdminUserPasswordRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var admin = adminLoginRepository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(admin);
        var refreshToken = jwtService.generateRefreshToken(admin);
        revokeAllUserTokens(admin);
        saveUserToken(admin, jwtToken);
        System.err.println(jwtToken);
        return AdminAuthenticationReponse.builder().
                token(jwtToken)
                .id(admin.getId())
                .code(admin.getCode())
                .name(admin.getName())
                .birthday(admin.getBirthday())
                .gender(admin.getGender())
                .address(admin.getAddress())
                .phoneNumber(admin.getPhoneNumber())
                .email(admin.getEmail())
                .username(admin.getUsername())
                .roleAdmin(admin.getRole())
                .status(admin.getStatus()).build();
    }

    @Override
    public AdminAuthenticationReponse changePassword(AdminPassRequest request, Principal connecteUser) {
        var admin=(Admin) ((UsernamePasswordAuthenticationToken) connecteUser).getPrincipal();
        if(!passwordEncoder.matches(request.getCurrentPassword(), admin.getPassword())){
            throw new IllegalStateException("Wrong password");
        };
        if(!request.getNewPassword().equals(request.getConfirmationPassword())){
            throw new IllegalStateException("password aren't the same");
        }
        admin.setPassword(passwordEncoder.encode(request.getNewPassword()));
        adminLoginRepository.save(admin);
        return AdminAuthenticationReponse.builder()
                .id(admin.getId())
                .code(admin.getCode())
                .name(admin.getName())
                .birthday(admin.getBirthday())
                .gender(admin.getGender())
                .address(admin.getAddress())
                .phoneNumber(admin.getPhoneNumber())
                .email(admin.getEmail())
                .username(admin.getUsername())
                .roleAdmin(admin.getRole())
                .status(admin.getStatus()).build();
    }

    @Override
    public AdminAuthenticationReponse updateInformation(String idAmin, AdminLoginRequest request, MultipartFile multipartFile) throws IOException {
        checkNull( isNullOrEmpty(request.getName()), request.getBirthday(), isNullOrEmpty(request.getAddress()), isNullOrEmpty(request.getPhoneNumber()), isNullOrEmpty(request.getEmail()), request);
        Admin admin = adminLoginRepository.findById(idAmin).orElse(null);
        admin.setName(request.getName());
        admin.setBirthday(request.getBirthday());
        admin.setGender(request.getGender());
        admin.setAddress(request.getAddress());
        admin.setPhoneNumber(request.getPhoneNumber());
        admin.setEmail(request.getEmail());
        if (multipartFile==null){
            admin.setAvatarUrl(null);
        }else {
            admin.setAvatarUrl(cloudinary.uploader()
                    .upload(multipartFile.getBytes(),
                            Map.of("id", UUID.randomUUID().toString()))
                    .get("url")
                    .toString());
        }
        admin.setStatus(Status.HOAT_DONG);
        adminLoginRepository.save(admin);
        return AdminAuthenticationReponse.builder().
                code(admin.getCode())
                .id(admin.getId())
                .name(admin.getName())
                .birthday(admin.getBirthday())
                .gender(admin.getGender())
                .address(admin.getAddress())
                .phoneNumber(admin.getPhoneNumber())
                .email(admin.getEmail())
                .avataUrl(admin.getAvatarUrl())
                .username(admin.getUsername())
                .status(admin.getStatus())
                .roleAdmin(admin.getRole())
                .build();
    }

    public void checkNull( boolean nullOrEmpty2, Long birthday, boolean nullOrEmpty3, boolean nullOrEmpty4, boolean nullOrEmpty5, AdminLoginRequest request) {
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

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    private void saveUserToken(Admin admin, String jwtToken) {
        var token = Token.builder()
                .admin(admin)
                .token(jwtToken)
                .tokenType(TypeToken.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        adminTokenRepository.save(token);
    }

    private void revokeAllUserTokens(Admin admin) {
        var validUserTokens = adminTokenRepository.findAllValidTokenByUser(admin.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        adminTokenRepository.saveAll(validUserTokens);
    }

}
