package core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BadCredentialException extends RuntimeException{
    public BadCredentialException(String username){
        super("Incorrect password for user: '"+username+"'.");
    }
}
