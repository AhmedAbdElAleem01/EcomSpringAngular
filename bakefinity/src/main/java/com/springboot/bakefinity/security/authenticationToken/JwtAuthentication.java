package com.springboot.bakefinity.security.authenticationToken;

import lombok.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthentication implements Authentication {
    private String token;
    private  Long userId;
    private  String email;
    public JwtAuthentication(String token) {
        this.token = token;
    }
    Collection<? extends GrantedAuthority> authorities;
    private boolean authenticated;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userId;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        authenticated=isAuthenticated;

    }

    @Override
    public String getName() {
        return token;
    }
}
