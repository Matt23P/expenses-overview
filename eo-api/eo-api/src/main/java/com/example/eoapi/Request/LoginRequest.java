package com.example.eoapi.Request;

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
public class LoginRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 2415980068122614705L;
    private String email;
    private String password;
}
