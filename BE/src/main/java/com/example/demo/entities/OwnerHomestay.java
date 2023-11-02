package com.example.demo.entities;

import com.example.demo.entities.base.PrimaryEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.demo.infrastructure.contant.EntityProperties;
import com.example.demo.infrastructure.contant.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "owner_homestay")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OwnerHomestay extends PrimaryEntity implements UserDetails{

    @Column(length = EntityProperties.LENGTH_CODE)
    private String code;

    @Column(length = EntityProperties.LENGTH_NAME)
    private String name;

    private Long birthday;

    private Boolean gender;

    private String address;

    @Column(length = EntityProperties.LENGTH_PHONE)
    private String phoneNumber;

    @Column(length = EntityProperties.LENGTH_EMAIL)
    private String email;

    private String username;

    private String password;

    private String avatarUrl;

    private Status status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
