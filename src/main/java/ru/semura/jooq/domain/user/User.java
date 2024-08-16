package ru.semura.jooq.domain.user;

import lombok.Getter;
import ru.semura.jooq.domain.user.objectvalue.Email;
import ru.semura.jooq.domain.user.objectvalue.UserId;
import ru.semura.jooq.domain.user.objectvalue.Username;

@Getter
public class User {
    private int id;
    private String displayName;
    private String email;
    private String username;


    public User(int id, String displayName, String email, String username) {
        this.id = id;
        this.displayName = displayName;
        this.email = email;
        this.username = username;
    }

    public static User createNewWithoutDisplayName(UserId id, Username username, Email email) {
        return new User(id.value(), null, email.value(), username.value());
    }

    public void modifyDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void modifyEmail(Email email) {
        this.email = email.value();
    }

}
