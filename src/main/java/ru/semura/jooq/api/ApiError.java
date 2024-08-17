package ru.semura.jooq.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.ResponseEntity;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiError<T>(
        String code,
        String message,
        T data
) {
    public static ResponseEntity<ApiError<Void>> of(ApiErrorCode code) {
        return ResponseEntity.status(code.getHttpStatusCode())
                .body(new ApiError<>(code.getCode(), code.getMessage(), null));
    }

    public static <T> ResponseEntity<ApiError<T>> of(ApiErrorCode code, T data) {
        return ResponseEntity.status(code.getHttpStatusCode())
                .body(new ApiError<>(code.getCode(), code.getMessage(), data));
    }

    public static ApiError<Void> of(String code, Exception e) {
        return new ApiError<>(code, e.getMessage(), null);
    }

}
