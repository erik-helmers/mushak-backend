package core.security;

import core.entities.users.SecurityUser;
import core.entities.users.User;

import java.util.Optional;

/**
 * USAGE HERE
 * By Erik Helmers, the 27/05/2018
 */
public interface UserAuthenticationService {

    Optional<String> login(String username, String password);
    Optional<SecurityUser> findByToken(String token);
    void logout(SecurityUser user);



}
