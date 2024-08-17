package ru.semura.jooq.api.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.semura.jooq.api.ApiError;
import ru.semura.jooq.api.ApiErrorCode;
import ru.semura.jooq.api.user.error.UsernameIsNotUniqueException;
import ru.semura.jooq.api.user.model.UserCreationRequest;
import ru.semura.jooq.api.user.model.UserResponse;
import ru.semura.jooq.query.UserReadRepository;
import ru.semura.jooq.usecase.user.UserCreationUseCase;

import java.util.Collection;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserCreationUseCase userCreationUseCase;
    private final UserReadRepository userReadRepository;

    @GetMapping
    public Collection<UserResponse> getAll() {
        return userReadRepository.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse createUser(@RequestBody @Valid UserCreationRequest request) {
        return userCreationUseCase.execute(request);
    }

    @ExceptionHandler
    public ResponseEntity<ApiError<Void>> handle(UsernameIsNotUniqueException e) {
        return ApiError.of(UserApiCode.USERNAME_IS_NOT_UNIQUE);
    }

    @ExceptionHandler
    public ResponseEntity<ApiError<Void>> handle(IllegalArgumentException e) {
        return ApiError.of(ApiErrorCode.valueOf(400));
    }
}
