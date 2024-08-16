package ru.semura.jooq.api;

public record Error<T>(
        String code,
        String message,
        T data
) {
    public static Error<?> of(String code, String message) {
        return new Error<>(code, message, null);
    }
}
