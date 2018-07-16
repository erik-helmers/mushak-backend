package core.entities.users;

import ch.qos.logback.classic.joran.action.LevelAction;
import easysqlite.annotations.declarations.Column;
import easysqlite.annotations.declarations.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@ToString
@FieldDefaults(level = AccessLevel.PUBLIC)
@Table("tokens")
public class Token {

    @Column
    String token;
    @Column
    long expireDate;
    @Column
    User.Id userID;

    public Token(){}

    @Builder
    public Token(String token, long expireDate, User.Id id) {
        this.token = token;
        this.expireDate = expireDate;
        this.userID = id;
    }

    @Override
    public int hashCode() {
        return token.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Token && token.equals(((Token)o).token);

    }
}
