package core.controller;

import core.entities.Response;
import core.security.UserAuthenticationService;
import core.security.UserRegistrationService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

/**
 * USAGE HERE
 * By Erik Helmers, the 25/05/2018
 */


@RestController
@RequestMapping("/public")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
@Log4j2
final class PublicController {


    @Autowired
    UserAuthenticationService authService;
    @Autowired
    UserRegistrationService registerService;

    @RequestMapping("/status")
    public Response getServerStatus(){
        return Response.builder()
                .info("Hello world!")
                .status(200)
                .build();
    }


    @PostMapping("/login")
    String login(
            final HttpServletRequest request,
            @RequestParam("username") final String username,
            @RequestParam("password") final String password) {
        log.debug("Received connection attempt with creditentials "+username+ " : "+password);
        return authService
                .login(username, password)
                .orElseThrow(() -> new RuntimeException("invalid login and/or password"));
    }

    @PostMapping("/register")
    String register(
            final HttpServletRequest request,
            @RequestParam("username") final String username,
            @RequestParam("password") final String password) {
        System.out.println("Created user "+username+"...");
        String output = registerService.register(username, password).toString();
        System.out.println("Success !");
        return output;


    }




}
