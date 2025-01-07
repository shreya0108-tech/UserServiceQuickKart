package com.proj.userservice.controllers;

import com.proj.userservice.models.User;
import com.proj.userservice.DTO.UserRequestDTO;
import com.proj.userservice.DTO.UserResponseDTO;
import com.proj.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public UserResponseDTO signup(@RequestBody UserRequestDTO userdto)
    {
        String name = userdto.getName();
        String email = userdto.getEmail();
        String pwd = userdto.getPwd();
        User user1 = userService.createUser(name,email,pwd);
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setName(user1.getName());
        userResponseDTO.setEmail(user1.getEmail());
        return userResponseDTO;
    }
}
