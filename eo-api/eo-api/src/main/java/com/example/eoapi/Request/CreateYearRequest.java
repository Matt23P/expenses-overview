package com.example.eoapi.Request;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class CreateYearRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -7467148639923426794L;
    private int userId;
    @NotNull
    private String year;
}
