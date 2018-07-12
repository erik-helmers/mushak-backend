package core.security.crudusers;

import core.entities.users.Token;
import core.entities.users.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;


//With help from https://octoperf.com/blog/2018/03/08/securing-rest-api-spring-security/#user-auth-uuid
@Service
public class InMemoryUsersTokens implements UserTokenCrudService {

    Map<String, Token> tokens = new HashMap<>();

    @Override
    public void add(Token token) {
        tokens.put(token.token, token);
    }

    @Override
    public void remove(String token) {
        tokens.remove(token);
    }

    @Override
    public Optional<Token> find(String token) {
        return Optional.ofNullable(tokens.get(token));
    }


}
