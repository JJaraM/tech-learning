package com.jjara.gateway;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

public class AuthenticationI implements Authentication {

    private boolean isAuthenticated = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.NO_AUTHORITIES;
    }

    @Override
    public Object getCredentials() {
        return new Object();
    }

    @Override
    public Object getDetails() {
        return new Object();
    }

    @Override
    public Object getPrincipal() {
        return new Object();
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        isAuthenticated = true;
    }

    @Override
    public String getName() {
        return "admin";
    }
}
