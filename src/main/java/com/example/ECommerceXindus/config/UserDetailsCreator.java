package com.example.ECommerceXindus.config;

import com.example.ECommerceXindus.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class UserDetailsCreator implements UserDetails {
    String username;
    String password;

    List<GrantedAuthority>authorities;
    public UserDetailsCreator(User user) {
        this.username=user.getUsername();
        this.password=user.getPassword();
        this.authorities=new ArrayList<>();
       String[]roles= user.getRoles().split(",");

        for (String role:roles) {
            SimpleGrantedAuthority authority=new SimpleGrantedAuthority(role);
            authorities.add(authority);
        }

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
