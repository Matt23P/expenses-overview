package com.example.eoapi.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @NotNull
    @Positive
    private double amount;
    @NotNull
    private String description;
    @NotNull
    private String type;
    @NotNull
    private boolean income;
    @NotNull
    private String currency;
    @NotNull
    private String year;
    @NotNull
    private String month;
    @NotNull
    private String day;
}
