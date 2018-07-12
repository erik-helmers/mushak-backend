package core;


import core.accounts.BasicAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {


    final
    BasicAuthentication auth;

    @Autowired
    public Application(BasicAuthentication auth) {
        this.auth = auth;
    }

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(Application.class, args);
    }


}
