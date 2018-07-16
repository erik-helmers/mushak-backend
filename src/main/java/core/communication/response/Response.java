package core.entities;

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

    public Response() {
        this.status = "success";
    }

    public Response(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String status;

    public HashMap<String, Object> results = new HashMap<>();
    public String message;

    public void addInfo(String title, Object message){
        results.put(title, message);
    }


    public void setMessage(String message){
        this.message = message;
    }
}
