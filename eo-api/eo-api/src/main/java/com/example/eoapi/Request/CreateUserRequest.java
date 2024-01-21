package com.example.eoapi.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    private String email;
    private String username;
    private String password;
    private String userCurrency;

    @Override
    public String toString() {
        return "\nemail: " + email + "\n" +
                "username: " + username + "\n" +
                "password: " + password + "\n" +
                "user currency: " + userCurrency;
    }
}
