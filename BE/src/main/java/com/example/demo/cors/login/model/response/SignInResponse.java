package com.example.demo.cors.login.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;


public interface SignInResponse {

    @Value("#{target.status}")
     Integer getStatus();

    @Value("#{target.code}")
     String getCode();

    @Value("#{target.name}")
     String getName();

    @Value("#{target.password}")
     String getPass();

    @Value("#{target.username}")
     String getUname();

    @Value("#{target.role}")
     String getRoleCode();

    @Value("#{target.quyen}")
     String getRoleName();
}
