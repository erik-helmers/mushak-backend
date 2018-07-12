package core.accounts.passwordstorage;


import core.entities.users.User;
import org.springframework.stereotype.Service;

public class NoHash implements PasswordHasher {


    @Override
    public void hash(User user) {

    }

    @Override
    public boolean verify(String password, User user) {
        return user.password.equals(password);
    }
}
