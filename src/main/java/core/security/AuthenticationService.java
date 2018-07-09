package core.security;

import core.databasemanager.users.UserDatabase;
import core.entities.users.SecurityUser;
import core.entities.users.User;
import core.exceptions.BadCredentialException;
import core.exceptions.TokenNotFoundException;
import logger.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * USAGE HERE
 * By Erik Helmers, the 27/05/2018
 */

@Service
public class AuthenticationService implements UserAuthenticationService, UserRegistrationService{

    private final UserDatabase user_db;

    Map<String, SecurityUser> users = new HashMap<>();

    @Autowired
    public AuthenticationService(UserDatabase user_db) {
        this.user_db = user_db;
    }


    @Override
    public SecurityUser register(String username, String password) {
        //TODO: password encryption
        User new_user = User.builder().id(0L).username(username).password(password).build();
        user_db.write.register(new_user);
        Log.info("Security", "Created accounted with '"+username + "' username");
        return SecurityUser.builder().username(username).password(password).build();
    }



    private boolean correct_password(String username, String password){
        Optional<User> user = user_db.read.read_from(username);
        return user.isPresent() && user.get().password.equals(password);
    }


    @Override
    public Optional<String> login(String username, String password) {
        final String token = UUID.randomUUID().toString();
        if (!correct_password(username, password))
            throw new BadCredentialException(username);
        users.put(token, SecurityUser.builder().username(username).password(password).sessionId(token).build());
        return Optional.of(token);
    }

    @Override
    public Optional<SecurityUser> findByToken(String token) throws TokenNotFoundException {
        return Optional.ofNullable(users.get(token));
    }

    @Override
    public void logout(SecurityUser user) {
        users.remove(user.sessionId);
    }



    @Override
    public boolean isEnabled() {
        return true;
    }
}
