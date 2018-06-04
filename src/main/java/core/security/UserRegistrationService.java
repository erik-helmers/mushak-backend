package core.security;

import core.entities.users.SecurityUser;
import core.entities.users.User;

import java.util.Optional;

public interface UserRegistrationService {

    boolean isEnabled();

    SecurityUser register(String username, String password);
}
