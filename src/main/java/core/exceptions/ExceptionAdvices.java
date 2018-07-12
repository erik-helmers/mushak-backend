package core.exceptions;

import core.entities.Response;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Advice the public controller
 * By Erik Helmers, the 27/05/2018
 */

@ControllerAdvice
public class ExceptionAdvices {
    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Response userNotFoundExceptionHandler(UserNotFoundException e){
        Response output = new Response();
        output.addInfo("error", e.toString());
        return output;
    }

    @ResponseBody
    @ExceptionHandler(UsernameExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    VndErrors userNotFoundExceptionHandler(UsernameExistsException e){
        return new VndErrors("error", e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(BadCredentialException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    VndErrors userNotFoundExceptionHandler(BadCredentialException e){
        return new VndErrors("error", e.getMessage());
    }

}
