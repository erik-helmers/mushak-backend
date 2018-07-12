package core.accounts;

import core.databasemanager.users.UserDatabase;
import core.entities.Response;
import core.entities.users.User;
import core.security.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * USAGE HERE
 * By Erik Helmers, the 27/05/2018
 */
public abstract class UserAuthenticationService {

    TokenAuthenticationService tokens;
    UserDatabase user_db;

    @Autowired
    public UserAuthenticationService(TokenAuthenticationService tokens, UserDatabase user_db) {
        this.tokens = tokens;
        this.user_db = user_db;
    }

    public abstract Response login(LoginForm login);
    public abstract void logout(String user);

    public boolean isEnabled() { return true; }



}
