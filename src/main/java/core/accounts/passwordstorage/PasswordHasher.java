package core.accounts.passwordstorage;

import core.entities.users.User;

public interface PasswordHasher {

    void hash(User user);
    boolean verify(String password, User user);


}
