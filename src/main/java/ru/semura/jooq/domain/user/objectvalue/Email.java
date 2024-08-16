package ru.semura.jooq.domain.user.objectvalue;

public record Email(
        String value
) {

    private static final String EMAIL_REGEX = "[^@]+@[^@]+";

    public Email {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Email must not be blank");
        }
        if (!value.matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException("Email must be valid");
        }
    }
}
