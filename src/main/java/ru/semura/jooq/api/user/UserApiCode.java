package ru.semura.jooq.api.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import ru.semura.jooq.api.ApiErrorCode;

@RequiredArgsConstructor
public enum UserApiCode implements ApiErrorCode {
    USERNAME_IS_NOT_VALID(HttpStatus.BAD_REQUEST, "Username is not valid"),
    USERNAME_IS_NOT_UNIQUE(HttpStatus.BAD_REQUEST, "Username is not unique"),
    ;

    private final HttpStatusCode httpStatus;
    private final String message;


    @Override
    public String getCode() {
        return name();
    }

    @Override
    public HttpStatusCode getHttpStatusCode() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
