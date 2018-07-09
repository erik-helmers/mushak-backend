package core.security.crudusers;

import core.entities.users.SecurityUser;

import java.util.Optional;

public interface UserCrudService {
    SecurityUser save(SecurityUser user);
    Optional<SecurityUser> find(String id);
    Optional<SecurityUser> findByUsername(String username);
}
