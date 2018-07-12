package core.accounts;

import core.accounts.userdatachecker.UserRegistrationInfoChecker;
import core.databasemanager.users.UserDatabase;
import core.entities.Response;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class UserRegistrationService {



    boolean isEnabled() { return true; }
    public abstract Response register(RegisterForm form);


}
