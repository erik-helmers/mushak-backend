package security;

import core.accounts.LoginForm;
import core.exceptions.UsernameExistsException;
import core.accounts.BasicAuthentication;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class BasicAuthenticationTest {

    @Autowired
    private BasicAuthentication authService;

    @Configuration
    @ComponentScan("core")
    public static class Config {
    }

    @Test
    public void login(){
        LoginForm login = new LoginForm("erik", "none");
        authService.login(login);
    }

    @Test(expected = UsernameExistsException.class)
    public void register_fail(){

    }

    @Test
    public void register_success(){

    }
}