package com.proj.userservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.proj.userservice.DTO.*;
import com.proj.userservice.exceptions.PasswordNotMatchException;
import com.proj.userservice.exceptions.TokenNotFoundException;
import com.proj.userservice.exceptions.UserNotFoundException;
import com.proj.userservice.models.Token;
import com.proj.userservice.models.User;
import com.proj.userservice.services.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public UserResponseDTO signup(@RequestBody UserRequestDTO userdto) throws JsonProcessingException {
        String name = userdto.getName();
        String email = userdto.getEmail();
        String pwd = userdto.getPwd();
        User user1 = userService.createUser(name, email, pwd);
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setName(user1.getName());
        userResponseDTO.setEmail(user1.getEmail());
        return userResponseDTO;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO loginDTO) throws UserNotFoundException, PasswordNotMatchException {
        Token token = userService.login(loginDTO.getEmail(),loginDTO.getPwd());
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setExpiry_dt(token.getExpiry_at());
        loginResponseDTO.setToken(token.getValue());
        return loginResponseDTO;
        //storing the token
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDTO logoutRequestDTO) throws TokenNotFoundException {
        userService.logout(logoutRequestDTO.getToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/validate/{token}")
    public UserDTO validateToken(@PathVariable("token") @NonNull String token) throws TokenNotFoundException {
        User user = userService.validateToken(token);
        return UserDTO.toDTO(user);
    }
}
