package core.security.passwordstorage;

import com.kosprov.jargon2.api.Jargon2.*;
import core.entities.users.SecurityUser;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import static com.kosprov.jargon2.api.Jargon2.jargon2Hasher;
import static com.kosprov.jargon2.api.Jargon2.jargon2Verifier;

@Service
public class SecureHasher implements PasswordHasher {

    private final Hasher hasher;
    private final Verifier verifier;

    private static final int saltLength = 32;

    public SecureHasher() {

        hasher = jargon2Hasher()
                .type(Type.ARGON2d)
                .memoryCost(65536)
                .timeCost(3)
                .parallelism(4)
                .saltLength(saltLength)
                .hashLength(16);
        verifier = jargon2Verifier();
    }


    @Override
    public SecurityUser hash(SecurityUser user) {
        //Use SecureRandom over Random for security
        SecureRandom random = new SecureRandom();
        //Salt of hash result's size : here 256 bits
        byte[] salt = new byte[saltLength];

        //Random salt
        random.nextBytes(salt);
        //Use of Argon2
        user.password = hasher
                .password(user.password.getBytes())
                .salt(salt)
                .encodedHash();
        user.salt = new String(salt, StandardCharsets.US_ASCII);
        return user;
    }

    @Override
    public boolean verify(String password, SecurityUser user) {
        return verifier
                .password(password.getBytes())
                .salt(user.salt.getBytes())
                .hash(user.password)
                .verifyEncoded();

    }
}
