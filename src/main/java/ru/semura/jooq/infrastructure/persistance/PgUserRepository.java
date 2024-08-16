package ru.semura.jooq.infrastructure.persistance;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.InsertResultStep;
import org.jooq.Record;
import org.jooq.Record1;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.semura.jooq.domain.user.User;
import ru.semura.jooq.domain.user.UserRepository;
import ru.semura.jooq.domain.user.objectvalue.UserId;
import ru.semura.jooq.domain.user.objectvalue.Username;

import java.util.Objects;
import java.util.Optional;

import static org.jooq.impl.DSL.*;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PgUserRepository implements UserRepository {
    private final DSLContext create;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public UserId nextId() {
        String sql = create.select(sequence(name("seq_user")).nextval())
                .getSQL();

        return jdbcTemplate.queryForObject(sql, (rs, i) -> new UserId(rs.getInt(1)));
    }

    @Override
    public Optional<User> findByUsername(Username username) {
        String sql = create.select(field("user_id"), field("username"), field("email"))
                .from(table("t_user"))
                .where(field("username").eq(username.value()))
                .getSQL();

        return jdbcTemplate.queryForStream(sql, (rs, i) -> new User(
                rs.getInt("user_id"),
                null,
                rs.getString("username"),
                rs.getString("email")
        )).findFirst();
    }

    @Override
    public boolean isUniqueUsername(Username username) {
        int count = create.selectCount().from(table("t_user"))
                .where(field("username").eq(username.value()))
                .fetchOne()
                .value1();
        return count == 0;
    }

    @Override
    public User save(User user) {
        int executed = create.insertInto(table("t_user"))
                .columns(field("user_id"), field("username"), field("email"))
                .values(user.getId(), user.getUsername(), user.getEmail())
                .onConflict(field("username"))
                .doNothing()
                .execute();
        if (executed != 1) {
            throw new IllegalStateException("User already exists");
        }
        return user;
    }
}
