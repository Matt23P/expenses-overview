package com.example.eoapi.Service;

import com.example.eoapi.Request.CreateUserRequest;
import com.example.eoapi.Request.LoginRequest;
import com.example.eoapi.Response.LoginResponse;
import com.example.eoapi.Response.StatusMessage;

public interface UserService {
    StatusMessage signUpUser(CreateUserRequest request);

    LoginResponse loginUser(LoginRequest request);
}
