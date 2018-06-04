package core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UsernameExistsException extends RuntimeException{
    public UsernameExistsException(String username){
        super("Username already exists: '"+username+"'.");
    }
}
