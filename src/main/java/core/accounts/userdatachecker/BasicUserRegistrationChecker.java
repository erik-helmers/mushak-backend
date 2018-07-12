package core.accounts.userdatachecker;

import core.databasemanager.users.UserDatabase;
import core.entities.Response;
import core.exceptions.ServerError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicUserRegistrationChecker implements UserRegistrationInfoChecker {

    @Autowired
    public BasicUserRegistrationChecker(UserDatabase user_db) {
        this.user_db = user_db;
    }

    private UserDatabase user_db;

    @Override
    public void valid_username(String username, Response response) {

        try {

            if (!user_db.users.search("username", username).isEmpty()){
                response.addError(ServerError.USERNAME_ALREADY_TAKEN);
            }
            if (username.length() < 3){
                response.addError(ServerError.USERNAME_TOO_SHORT);
            }
            if (username.length() > 64){
                response.addError(ServerError.USERNAME_TOO_LONG);
            }

        } catch (Exception e) {
            throw new Error(e);
        }

    }

    @Override
    public void valid_password(String password, Response response) {
        if (password.length() < 4){
            response.addError(ServerError.PASSWORD_TOO_SHORT);
        }
        if (password.length() > 64){
            response.addError(ServerError.PASSWORD_TOO_LONG);
        }
    }
}
