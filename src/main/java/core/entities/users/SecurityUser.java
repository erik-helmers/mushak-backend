package core.entities.users;

import core.entities.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * USAGE HERE
 * By Erik Helmers, the 27/05/2018
 */


@ToString
@Getter
@FieldDefaults(level = AccessLevel.PUBLIC)
public class SecurityUser extends Entity<User.Id> implements UserDetails {

    @Builder
    public SecurityUser(Long id,
                        String username,
                        String password,
                        String sessionId, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {

        super(new User.Id(id));
        this.username = username;
        this.password = password;
        this.sessionId = sessionId;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    String username;
    String password;
    String sessionId;
    boolean accountNonExpired;
    boolean accountNonLocked;
    boolean credentialsNonExpired;
    boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


}
