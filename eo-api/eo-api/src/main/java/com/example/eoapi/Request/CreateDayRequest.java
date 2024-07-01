package com.example.eoapi.Request;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class CreateDayRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1066505946095767622L;
    private int userId;
    private int yearId;
    private int monthId;
    private String day;
}
