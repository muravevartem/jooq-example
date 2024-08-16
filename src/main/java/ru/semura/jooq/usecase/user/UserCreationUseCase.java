package ru.semura.jooq.usecase.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.semura.jooq.api.user.model.UserCreationRequest;
import ru.semura.jooq.api.user.model.UserResponse;
import ru.semura.jooq.common.UseCase;
import ru.semura.jooq.domain.user.User;
import ru.semura.jooq.domain.user.UserRepository;
import ru.semura.jooq.domain.user.objectvalue.Email;
import ru.semura.jooq.domain.user.objectvalue.UserId;
import ru.semura.jooq.domain.user.objectvalue.Username;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCreationUseCase implements UseCase<UserCreationRequest, UserResponse> {
    private final UserRepository userRepository;

    @Override
    public UserResponse execute(UserCreationRequest request) {
        Username username = new Username(request.username());
        if (!userRepository.isUniqueUsername(username)) {
            throw new IllegalArgumentException("Username is not unique");
        }

        Email email = new Email(request.email());

        UserId userId = userRepository.nextId();
        User newUser = User.createNewWithoutDisplayName(userId, username, email);
        userRepository.save(newUser);
        return new UserResponse(newUser.getId(), null, newUser.getUsername(), newUser.getEmail());
    }
}
