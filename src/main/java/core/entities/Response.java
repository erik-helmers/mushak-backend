package core.entities;

import core.exceptions.ServerError;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.apache.catalina.Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * USAGE HERE
 * By Erik Helmers, the 25/05/2018
 */


@FieldDefaults(level = AccessLevel.PUBLIC)
public class Response {

    public Response() {}

    public Response(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int status;
    public List<ServerError> errors = new ArrayList<>();
    public HashMap<String, String> results = new HashMap<>();
    public String message;

    public void addError(ServerError error){
        errors.add(error);
    }
    public boolean hasError(){
        return !errors.isEmpty();
    }

    public void addInfo(String title, String message){
        results.put(title, message);
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message){
        this.message = message;
    }
}
