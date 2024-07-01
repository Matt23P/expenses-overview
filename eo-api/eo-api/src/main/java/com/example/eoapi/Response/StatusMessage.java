package com.example.eoapi.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusMessage implements Serializable {
    @Serial
    private static final long serialVersionUID = -1237638638145660622L;
    private boolean status;
    private List<String> error;
}
