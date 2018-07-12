package core.controller;

import core.accounts.LoginForm;
import core.accounts.RegisterForm;
import core.entities.Response;
import core.accounts.UserAuthenticationService;
import core.accounts.UserRegistrationService;
import lombok.AllArgsConstructor;
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




    @PostMapping("/login")
    public Response login(final HttpServletRequest request,
                          @RequestBody LoginForm form){
        return authService.login(form);
    }


    @PostMapping("/register")
    public Response register(@RequestBody RegisterForm form){
        return registerService.register(form);
    }




}
