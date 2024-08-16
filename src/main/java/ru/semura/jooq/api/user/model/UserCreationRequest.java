package ru.semura.jooq.api.user.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserCreationRequest(
        @NotBlank
        String username,

        @Email
        String email
) {
}
