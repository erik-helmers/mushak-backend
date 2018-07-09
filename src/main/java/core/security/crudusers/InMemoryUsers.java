package core.security.crudusers;

import core.entities.users.SecurityUser;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;


//With help from https://octoperf.com/blog/2018/03/08/securing-rest-api-spring-security/#user-auth-uuid
@Service
public class InMemoryUsers implements UserCrudService {

    Map<String , SecurityUser> users = new HashMap<>();

    @Override
    public SecurityUser save(SecurityUser user) {
        return users.put(user.getSessionId(), user);
    }

    @Override
    public Optional<SecurityUser> find(String sessionId) {
        return Optional.ofNullable(users.get(sessionId));
    }

    @Override
    public Optional<SecurityUser> findByUsername(String username) {
        return users
                .values()
                .stream()
                .filter(x -> Objects.equals(username, x.getUsername()))
                .findFirst();
    }
}
