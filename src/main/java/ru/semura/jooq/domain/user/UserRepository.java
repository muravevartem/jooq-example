package ru.semura.jooq.domain.user;

import ru.semura.jooq.domain.user.objectvalue.UserId;
import ru.semura.jooq.domain.user.objectvalue.Username;

import java.util.Optional;

public interface UserRepository {
    UserId nextId();

    Optional<User> findByUsername(Username username);

    boolean isUniqueUsername(Username username);

    User save(User user);
}
