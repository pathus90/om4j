package io.github.pathus90.om4j.model.response;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {

    SUCCESS(200), CREATED(201);

    private final int code;

    /**
     * Checks if the provided HTTP status code exists in the list of successful HTTP status codes.
     *
     * @param code The HTTP status code to check.
     * @return {@code true} if the code exists in the list of successful HTTP status codes; otherwise, {@code false}.
     */
    public static boolean containsHttpStatusCode(Integer code) {
        return Arrays.stream(ResponseCode.values())
                .anyMatch(responseCode -> responseCode.getCode() == code);
    }

}