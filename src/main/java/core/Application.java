package core;


import core.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class Application {


    final
    AuthenticationService auth;

    @Autowired
    public Application(AuthenticationService auth) {
        this.auth = auth;
    }

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(Application.class, args);
    }


}
