package security;

import logger.Log;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import utils.Time;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class TokenGeneration {

    @Test
    public void generate_uuid() throws UnsupportedEncodingException {

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
        String alternativ = new String(output_byte, StandardCharsets.US_ASCII);
        Log.debug_value("alternativ", alternativ);
        //return output;

    }


    public static byte[] longToByte(long l){
        byte[] result = new byte[4];
        for (int i = 0; i<3; i++){
            result[i] = (byte)(l & 0xFF);
            l >>= Byte.SIZE;
        }
        return result;
    }



}
