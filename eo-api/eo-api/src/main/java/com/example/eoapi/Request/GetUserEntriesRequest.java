package com.example.eoapi.Request;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class GetUserEntriesRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 5354916756056963445L;
    private int userId;
    private String year;
    private String month;
    private String day;
}
