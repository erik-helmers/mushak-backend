package core.security.crudusers;

import core.entities.users.Token;
import core.entities.users.User;
import java.util.Optional;

public interface UserTokenCrudService {
    void add(Token token);
    void remove(String token);
    Optional<Token> find(String token);

}
