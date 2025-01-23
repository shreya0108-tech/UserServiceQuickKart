package com.proj.userservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity

public class Token extends BaseModel{
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpiry_at() {
        return expiryAt;
    }

    public void setExpiry_at(Date expiry_at) {
        this.expiryAt = expiry_at;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @ManyToOne
    private User user;
    @Column(name = "expiry_at")
    private Date expiryAt;
    private boolean isDeleted;
}
