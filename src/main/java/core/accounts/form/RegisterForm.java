package core.accounts;

import org.springframework.lang.Nullable;

import javax.validation.constraints.Null;

public class RegisterForm {

    public String username;
    public String password;

    @Nullable
    public String name;

    public RegisterForm() { }

    public RegisterForm(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;

    }
}
