package io.github.pathus90.om4j.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiError {
    private int code;
    private String message;
    private String description;
}
