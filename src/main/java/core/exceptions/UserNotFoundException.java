package core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * USAGE HERE
 * By Erik Helmers, the 27/05/2018
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String userId){
        super("could not find user '"+userId+"'.");
    }
}
