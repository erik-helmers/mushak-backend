package core.entities;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

/**
 * USAGE HERE
 * By Erik Helmers, the 25/05/2018
 */


@FieldDefaults(makeFinal = true, level = AccessLevel.PUBLIC)
public class Response {


    int status;
    String info;
    String body;

    @Builder
    public Response( int status, String info, String body) {
        this.status = status;
        this.info = info;
        this.body = body;
    }





}
