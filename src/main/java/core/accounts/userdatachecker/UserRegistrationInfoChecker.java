package core.accounts.userdatachecker;


import core.entities.Response;

public interface UserRegistrationInfoChecker {

    void valid_username(String username, Response response);
    void valid_password(String password, Response response);

}
