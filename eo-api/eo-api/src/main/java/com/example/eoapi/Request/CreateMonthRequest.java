package com.example.eoapi.Request;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class CreateMonthRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 7430585846605967356L;
    private int userId;
    private int yearId;
    private String month;
}
