package io.github.pathus90.om4j.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MobileMoneyException extends RuntimeException {

    private String message;
    private int code;
    private String description;

    public MobileMoneyException(String message) {
        super(message);
        this.message = message;
    }

    public MobileMoneyException(int code, String message, String description) {
        super(message);
        this.message = message;
        this.code = code;
        this.description = description;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
