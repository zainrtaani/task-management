package com.taski.taskmanagement.model.auth;

import com.taski.taskmanagement.entity.user.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtAuthResponse {

    private UserDetails user;
    private String accessToken;
    private long expiresIn;

    public JwtAuthResponse(UserDetails user,
                           String accessToken, long expiresIn) {
        this.user = user;
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }


    public User getUser(){
        return ((com.taski.taskmanagement.model.auth.UserDetailsImpl)user).getUserEntity();
    }

    public Collection<String> getRoles(){
        return ((com.taski.taskmanagement.model.auth.UserDetailsImpl)user).getRoles();
    }


}
