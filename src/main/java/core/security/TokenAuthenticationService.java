package core.security;

import core.entities.Response;
import core.entities.users.Token;
import core.entities.users.User;
import core.security.crudusers.UserTokenCrudService;
import utils.Time;

public abstract class TokenAuthenticationService {

    public abstract void register_session(User user, Response response);
    public abstract void logout(String token);

    public abstract User validate(String token, Response response);

    protected long getExpireDate(){
        return Time.currentTime()+getTokenLifespan();
    }

    /**
     * Life span of a token in milliseconds
     * @return Life span of a token in milliseconds
     */
    protected long getTokenLifespan(){
        return 10*3600*1000;
    }
}
