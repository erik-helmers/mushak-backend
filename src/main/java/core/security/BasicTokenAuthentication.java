package core.security;

import core.databasemanager.users.UserDatabase;
import core.entities.Response;
import core.entities.users.Token;
import core.entities.users.User;
import core.exceptions.ServerError;
import core.security.crudusers.UserTokenCrudService;
import logger.Log;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.Time;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Optional;

@Service
public class BasicTokenAuthentication extends TokenAuthenticationService {

    UserTokenCrudService crud;
    UserDatabase user_db;

    @Autowired
    public BasicTokenAuthentication(UserTokenCrudService crud, UserDatabase user_db) {
        this.crud = crud;
        this.user_db = user_db;
    }

    @Override
    public void register_session(User user, Response response) {

        String token = generate_uuid();


        crud.add(new Token(token, getExpireDate(), user.id));

        response.addInfo("token", token);
    }


    /**
     * Funny almost efficient UUID
     * Merge 12 random bytes + 4 of time (time is useless from a security point of view)
     * And encode it in Base64
     * -> 79228162514264337593543950336 possibilites each millisecond
     * @return Base64 encoded string UUID
     */
    private String generate_uuid()  {

        int random_length = 12;
        int time_length = 4;

        byte[] output_byte = new byte[random_length+time_length];

        //12 byte long secure random
        SecureRandom random = new SecureRandom();
        byte[] random_part = new byte[random_length];
        random.nextBytes(random_part);
        System.arraycopy(random_part, 0, output_byte, 0, random_length);

        //merged with 4 less significant bytes of time
        long currentTime = System.currentTimeMillis();
        for (int i=random_length; i<output_byte.length; i++){
            output_byte[i] = (byte)(currentTime & 0xFF);
            currentTime >>= 8;
        }

        //Converted to base64 byte
        String output = Base64.encodeBase64String(output_byte);
        Log.debug_value("UUID", output);
        return output;

    }

    @Override
    public void logout(String token) {
        crud.remove(token);
    }

    @Override
    public User validate(String token, Response response) {
        Optional<Token> stoken = crud.find(token);
        if (!stoken.isPresent()){
            response.addError(ServerError.BAD_TOKEN);
            return null;
        }
        if (stoken.get().expireDate < Time.currentTime()){
            response.addError(ServerError.EXPIRED_TOKEN);
            //TODO: delete token
            return null;
        }

        return user_db.users.search("id", stoken.get().userID.represent()).get(0);

    }
}
