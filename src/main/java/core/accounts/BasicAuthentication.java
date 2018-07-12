package core.accounts;

import core.accounts.passwordstorage.PasswordHasher;
import core.databasemanager.users.UserDatabase;
import core.entities.Response;

import core.entities.users.User;
import core.exceptions.ServerError;
import core.security.TokenAuthenticationService;
import logger.Log;
import org.springframework.stereotype.Service;

/**
 * USAGE HERE
 * By Erik Helmers, the 27/05/2018
 */

@Service
public class BasicAuthentication extends UserAuthenticationService {

    private PasswordHasher hasher;

    public BasicAuthentication(TokenAuthenticationService tokens, UserDatabase user_db, PasswordHasher hasher) {
        super(tokens, user_db);
        this.hasher = hasher;
    }


    @Override
    public void logout(String user) {
        tokens.logout(user);
    }


    @Override
    public Response login(LoginForm login) {
        Response response = new Response();

        User user;
        try {
            user = user_db.users.search("username", login.username).get(0);
        } catch (IndexOutOfBoundsException e){
            response.addError(ServerError.USERNAME_NOT_FOUND);
            return response;
        } catch (Exception e) {
            response.addError(ServerError.INTERNAL_ERROR);
            Log.debug_value("error", e);
            return response;
        }

        if (!correct_password(user, login.password)){
            response.addError(ServerError.INCORRECT_PASSWORD);
            return response;
        }


        tokens.register_session(user, response);

        return response;
    }


    private boolean correct_password(User user, String password){
        return hasher.verify(password, user);
    }



    //endregion

    @Override
    public boolean isEnabled() {
        return true;
    }
}
