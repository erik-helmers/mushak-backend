package core.security;

import core.accounts.UserAuthenticationService;
import core.entities.Response;
import core.entities.users.User;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
public final class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {


    TokenAuthenticationService auth;

    @Autowired
    public TokenAuthenticationProvider(TokenAuthenticationService auth){
        this.auth = auth;
    }

    @Override
    protected void additionalAuthenticationChecks(final UserDetails d, final UsernamePasswordAuthenticationToken auth) {
        // Nothing to do
    }

    @Override
    protected UserDetails retrieveUser(final String username, final UsernamePasswordAuthenticationToken authentication) {
        final Object token = authentication.getCredentials();
        Response response = new Response();

        if (token == null) {
            throw new Error("mmmh, credentials is null");
        }

        User user = auth.validate(token.toString(), response);
        if (response.hasError()){
            throw new BadCredentialsException(response.errors.get(0).message);
        }
        return user;
    }
}