package by.edu.webstore.util;

import java.math.BigInteger;
import java.util.Base64;

/**
 * {@code PasswordEncoder} util class to help encode password
 */
public class PasswordEncoder {

    /**
     * {@code encode} method to encode password use {@link java.util.Base64}
     *
     * @param password - password before encrypt
     * @return encrypted password as String
     */
    public static String pasEncode(String password) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] bytesEncoded = encoder.encode(password.getBytes());
        BigInteger bigInt = new BigInteger(1, bytesEncoded);
        String resHex = bigInt.toString(16);
        return resHex;
    }
}

