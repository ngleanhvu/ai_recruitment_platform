package com.ngleanhvu.common.response;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
        int status,
        String message,
        String path,
        LocalDateTime timestamp,
        Map<String, String> errors
) {

    public static ErrorResponse of(
            int status,
            String message,
            String path
    ) {
        return new ErrorResponse(
                status,
                message,
                path,
                LocalDateTime.now(),
                null
        );
    }
}