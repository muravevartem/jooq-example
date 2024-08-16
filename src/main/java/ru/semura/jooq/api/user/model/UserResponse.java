package ru.semura.jooq.api.user.model;

public record UserResponse(
        int id,
        String displayName,
        String email,
        String username
) {
}
