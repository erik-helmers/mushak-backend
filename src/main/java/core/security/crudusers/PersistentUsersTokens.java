package core.security.crudusers;

import core.databasemanager.users.UserDatabase;
import core.entities.users.Token;
import core.entities.users.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.Optional;


public class PersistentUsersTokens implements UserTokenCrudService {

    @Autowired
    UserDatabase users_db;

    @Override
    public void add(Token token) {

        users_db.tokens.save(token);
    }

    @Override
    public void remove(String token) {

    }

    @Override
    public Optional<Token> find(String token) {

        return Optional.empty();

    }


}
