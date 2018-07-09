package core.security.passwordstorage;

import core.entities.users.SecurityUser;

public class NoHash implements PasswordHasher {


    @Override
    public SecurityUser hash(SecurityUser user) {
        return user;
    }

    @Override
    public boolean verify(String password, SecurityUser user) {
        return user.password.equals(password);
    }
}
