package ru.semura.jooq.api.user.error;

public class UsernameIsNotUniqueException extends RuntimeException{
    public UsernameIsNotUniqueException() {
        super("Username is not unique");
    }
}
