package com.example.demo.entities;

import com.example.demo.entities.base.PrimaryEntity;
import com.example.demo.infrastructure.contant.role.RoleOwner;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.demo.infrastructure.contant.EntityProperties;
import com.example.demo.infrastructure.contant.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "owner_homestay")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OwnerHomestay extends PrimaryEntity implements UserDetails{

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

    private RoleOwner role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
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
