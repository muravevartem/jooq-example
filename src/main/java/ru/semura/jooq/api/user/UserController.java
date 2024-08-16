package ru.semura.jooq.api.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.semura.jooq.api.Error;
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

    @PostMapping
    public UserResponse createUser(@RequestBody @Valid UserCreationRequest request) {
        return userCreationUseCase.execute(request);
    }

    @ExceptionHandler
    public Error<?> handle(IllegalArgumentException e) {
        return Error.of("bad_request", e.getMessage());
    }
}
