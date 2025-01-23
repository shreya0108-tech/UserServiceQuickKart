package com.proj.userservice.DTO;

import com.proj.userservice.models.Role;
import com.proj.userservice.models.User;

import java.util.List;

public class UserDTO {
    private String name;
    private String email;
    private List<Role> roles;
    private boolean isEmailVerified;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    public static UserDTO toDTO(User user) {
        if (user != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setName(user.getName());
            userDTO.setEmail(user.getEmail());
            userDTO.setRoles(user.getRoles());
            userDTO.setEmailVerified(user.isEmailVerified());
            return userDTO;
        }
        return null;
    }
}
