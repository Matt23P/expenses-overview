package com.example.eoapi.Response;

import com.example.eoapi.DTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -2664115487530822723L;
    private UserDTO user;
    private boolean status;
    private String error;
}
