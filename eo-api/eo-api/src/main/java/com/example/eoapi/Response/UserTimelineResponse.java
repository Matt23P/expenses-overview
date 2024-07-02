package com.example.eoapi.Response;

import com.example.eoapi.Entity.Days;
import com.example.eoapi.Entity.Months;
import com.example.eoapi.Entity.Years;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTimelineResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -3302508906810311339L;
    private List<Years> years;
    private List<Months> months;
    private List<Days> days;
    private boolean status;
    private List<String> errors;
}
