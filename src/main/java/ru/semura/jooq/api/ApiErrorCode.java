package ru.semura.jooq.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public interface ApiErrorCode {
    String getCode();
    HttpStatusCode getHttpStatusCode();
    String getMessage();

    static ApiErrorCode valueOf(int code) {
        return new HttpApiErrorCodeAdapter(code);
    }

    static ApiErrorCode valueOf(HttpStatusCode httpStatusCode) {
        return new HttpApiErrorCodeAdapter(httpStatusCode.value());
    }

    class HttpApiErrorCodeAdapter implements ApiErrorCode {
        private final HttpStatus httpStatus;

        public HttpApiErrorCodeAdapter(int httpStatusCode) {
            this.httpStatus = HttpStatus.valueOf(httpStatusCode);
        }

        @Override
        public String getCode() {
            return httpStatus.name();
        }

        @Override
        public HttpStatusCode getHttpStatusCode() {
            return httpStatus;
        }

        @Override
        public String getMessage() {
            return httpStatus.getReasonPhrase();
        }
    }
}
