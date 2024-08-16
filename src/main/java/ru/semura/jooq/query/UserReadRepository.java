package ru.semura.jooq.query;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.semura.jooq.api.user.model.UserResponse;

import java.util.Collection;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserReadRepository {
    private final DSLContext create;

    public Collection<UserResponse> findAll() {
        return create.select(field("user_id"), field("username"), field("email"))
                .from(table("t_user"))
                .orderBy(field("username").asc())
                .fetch(r -> new UserResponse(
                        r.get("user_id", Integer.class),
                        null,
                        r.get("username", String.class),
                        r.get("email", String.class
                )));
    }
}
