package core.controller;

import core.entities.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerInfoController {

    @RequestMapping("/status")
    public Response status(){
        return Response.builder()
                .status(200)
                .info("Everything running !")
                .build();
    }
}
