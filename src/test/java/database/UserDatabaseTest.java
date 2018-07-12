package database;

import core.databasemanager.users.UserDatabase;
import core.entities.users.User;
import core.exceptions.UserNotFoundException;
import core.exceptions.UsernameExistsException;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ThreadLocalRandom;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * USAGE HERE
 * By Erik Helmers, the 26/05/2018
 */

@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserDatabaseTest {
/**
    // WARNING ! ! Expects a user of id 0, name : user, password : password.
    @Autowired
    private UserDatabase db;

    @Configuration
    @ComponentScan("core")
    public static class Config{}

    @Test
    public void read_all(){
        log.error("Result: "+db.read.all_users().toString());
    }

    @Test
    public void read_from_id(){
        db.read.read_from(new User.Id(0L)).orElseThrow(() -> new UserNotFoundException("0"));
    }

    @Test
    public void read_from_username(){
        db.read.read_from("user").orElseThrow(() -> new UserNotFoundException("user"));
    }

    @Test(expected = UsernameExistsException.class)
    public void fail_write(){
        db.write.register(User.builder()
                .id(0L)
                .name("user")
                .password("password")
                .build());
    }

    @Test
    public void success_write(){
        int nb = ThreadLocalRandom.current().nextInt(1, 10000);
        db.write.register(User.builder()
                .id(0L)
                .name("testuser"+String.valueOf(nb))
                .password("password").build());
    }
**/
}
