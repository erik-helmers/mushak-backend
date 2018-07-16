package core.exceptions;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * RestError are ERRORS and not exceptions because
 * there is no need to catch them :
 * theey should be returned to the client
 */
public class RestError extends RuntimeException {

    protected ErrorType error;

    public List<SubError> sub_errors = new ArrayList<>();

    public void addError(SubError e){
        sub_errors.add(e);
    }

    public RestError(ErrorType error) {
        this.error = error;
    }

    public RestError(SubError info){
        this.error = info.type.type;
        addError(info);
    }


    public RestError(Throwable throwable, ErrorType error) {
        super(throwable);
        this.error = error;
    }

    public RestError(String s, Throwable throwable, boolean b, boolean b1, ErrorType error) {
        super(s, throwable, b, b1);
        this.error = error;
    }

    public ResponseEntity<Object> toEntity(){
        return new ResponseEntity<>(new Result(this), HttpStatus.valueOf(error.code));
    }

    @FieldDefaults(level = AccessLevel.PUBLIC)
    private class Result {
        String status;
        String type;
        String description;
        List<SubError> errors;

        Result(RestError error){
            status = "error";
            type = error.error.name();
            description = error.error.description;
            errors = error.sub_errors;
        }
    }

}
