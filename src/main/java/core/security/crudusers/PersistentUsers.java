package core.security.crudusers;

import core.databasemanager.users.UserDatabase;
import core.entities.users.SecurityUser;
import core.entities.users.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

//@Service("persistentUsers")
public class PersistentUsers implements UserCrudService {

    UserDatabase users_db;

    @Autowired
    public PersistentUsers(UserDatabase users_db){
        this.users_db = users_db;
    }

    @Override
    public SecurityUser save(SecurityUser security_user) {
        return null;
    }

    @Override
    public Optional<SecurityUser> find(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<SecurityUser> findByUsername(String username) {
        return Optional.empty();
    }

}
