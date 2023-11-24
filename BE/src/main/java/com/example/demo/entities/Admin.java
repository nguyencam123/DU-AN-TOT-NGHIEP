package com.example.demo.entities;

import com.example.demo.entities.base.PrimaryEntity;
import com.example.demo.infrastructure.contant.EntityProperties;
import com.example.demo.infrastructure.contant.role.RoleAdmin;
import com.example.demo.infrastructure.contant.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "admin")
@Getter
@Setter
public class Admin extends PrimaryEntity implements UserDetails {

    @Column(length = EntityProperties.LENGTH_CODE)
    @Nationalized
    private String code;

    @Column(length = EntityProperties.LENGTH_NAME)
    @Nationalized
    private String name;

    private Long birthday;

    private Boolean gender;

    @Nationalized
    private String address;

    @Column(length = EntityProperties.LENGTH_PHONE)
    @Nationalized
    private String phoneNumber;

    @Column(length = EntityProperties.LENGTH_EMAIL)
    @Nationalized
    private String email;

    @Nationalized
    private String username;

    @Nationalized
    private String password;

    @Nationalized
    private String avatarUrl;

    private Status status;

    private RoleAdmin roleAdmin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleAdmin.getAuthorities();
    }

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
