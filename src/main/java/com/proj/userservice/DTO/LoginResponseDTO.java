package com.proj.userservice.DTO;

import com.proj.userservice.models.Token;

import java.util.Date;

public class LoginResponseDTO {
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiry_dt() {
        return expiry_dt;
    }

    public void setExpiry_dt(Date expiry_dt) {
        this.expiry_dt = expiry_dt;
    }

    Date expiry_dt;
}
