package com.example.eoapi.Utils;

import com.example.eoapi.Request.CreateUserRequest;
import com.example.eoapi.Request.LoginRequest;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RequestValidator {

    public boolean validateLoginRequest(LoginRequest request) {
        boolean flag = true;

        if (request.getPassword() == null || request.getPassword().isEmpty() || request.getEmail() == null ||
            request.getEmail().isEmpty()) {
            flag = false;
        }

        return flag;
    }

    public boolean validateSignUpRequest(CreateUserRequest request) {
        boolean flag = true;

        if (request.getUserCurrency() == null || request.getUserCurrency().isEmpty() ||
            request.getUsername() == null || request.getUsername().isEmpty() ||
            request.getPassword() == null || request.getPassword().isEmpty() ||
            request.getEmail() == null || request.getEmail().isEmpty()) {
            flag = false;
        }

        return flag;
    }
}
