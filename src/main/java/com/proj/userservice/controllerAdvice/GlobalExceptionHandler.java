package com.proj.userservice.controllerAdvice;

import com.proj.userservice.DTO.ExceptionDto;
import com.proj.userservice.exceptions.PasswordNotMatchException;
import com.proj.userservice.exceptions.TokenNotFoundException;
import com.proj.userservice.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleUserNotFoundException()
    {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("User not found");
        exceptionDto.setResolution("Try with another user");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto,
                HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @ExceptionHandler(PasswordNotMatchException.class)
    public ResponseEntity<ExceptionDto> handlePasswordNotMatchException()
    {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Password not match");
        exceptionDto.setResolution("Try with another password");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(
                exceptionDto, HttpStatus.BAD_REQUEST
        );
        return responseEntity;
    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleTokenNotFoundException()
    {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Token not found");
        exceptionDto.setResolution("Try with another token");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(
                exceptionDto, HttpStatus.NOT_FOUND
        );
        return responseEntity;
    }
}