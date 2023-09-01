package com.example.birthday.thymeleafSecurity;

import com.example.birthday.repository.UserEntity;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
public class DBUserDetails implements UserDetails {
    private UserEntity user;

//    public DBUserDetails(UserEntity user) {
//        this.user = user;
//    }

    private String username;
    private String password;
    @Singular
    private List<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
