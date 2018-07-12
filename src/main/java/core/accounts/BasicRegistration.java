package core.accounts;

import core.accounts.passwordstorage.PasswordHasher;
import core.accounts.userdatachecker.UserRegistrationInfoChecker;
import core.databasemanager.users.UserDatabase;
import core.entities.Response;
import core.entities.users.MusicProfile;
import core.entities.users.User;
import core.entities.users.UserSettings;
import core.exceptions.ServerError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

@Service
public class BasicRegistration extends UserRegistrationService {

    UserDatabase user_db;
    UserRegistrationInfoChecker checker;
    PasswordHasher hasher;

    @Autowired
    public BasicRegistration(UserDatabase user_db, UserRegistrationInfoChecker checker, PasswordHasher hasher) {
        this.user_db = user_db;
        this.checker = checker;
        this.hasher = hasher;
    }

    @Override
    public Response register(RegisterForm form) {

        Response response = new Response();
        checker.valid_username(form.username, response);
        checker.valid_password(form.password, response);

        if (response.hasError()){
            return response;
        }

        User user = User.builder()
                .username(form.username)
                .name(form.name == null ? form.username : form.username)
                .password(form.password)
                .build();

        hasher.hash(user);

        user_db.register(user);


        response.message = "success";

        return response;
    }


}
