package com.example.eoapi.Response;

import com.example.eoapi.DTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private UserDTO user;
    private boolean status;
    private String error;
}
