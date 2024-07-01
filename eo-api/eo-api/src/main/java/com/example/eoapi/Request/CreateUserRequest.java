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
public class CreateUserRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 5156228535607853926L;
    private String email;
    private String username;
    private String password;
    private String userCurrency;
}
