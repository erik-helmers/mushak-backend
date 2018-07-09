package core.security.passwordstorage;

import core.entities.users.SecurityUser;

public interface PasswordHasher {

    SecurityUser hash(SecurityUser user);
    boolean verify(String password, SecurityUser user);


}
