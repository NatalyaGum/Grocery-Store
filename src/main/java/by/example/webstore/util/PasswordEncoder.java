package by.example.webstore.util;
import java.math.BigInteger;
import java.util.Base64;

public class PasswordEncoder {
    public static String pasEncode(String password) {
            Base64.Encoder encoder = Base64.getEncoder();
            byte[] bytesEncoded = encoder.encode(password.getBytes());
            BigInteger bigInt = new BigInteger(1, bytesEncoded);
            String resHex = bigInt.toString(16);
            return resHex;
        }
    }

