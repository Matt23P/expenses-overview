package com.example.eoapi.Service;

import com.example.eoapi.Request.CreateUserRequest;
import com.example.eoapi.Response.StatusMessage;

public interface UserService {
    StatusMessage signUpUser(CreateUserRequest request);
}
