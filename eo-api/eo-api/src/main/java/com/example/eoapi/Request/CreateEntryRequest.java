package com.example.eoapi.Request;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class CreateEntryRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -8674563449699289644L;
    private int userId;
    private double amount;
    private String description;
    private String type;
    private boolean income;
    private String currency;
    private String year;
    private String month;
    private String day;
}
