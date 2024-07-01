package com.example.eoapi.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -4073113352673849174L;
    private int id;
    private String username;
    private String email;
    private LocalDate creationDate;
    private String userCurrency;
}
