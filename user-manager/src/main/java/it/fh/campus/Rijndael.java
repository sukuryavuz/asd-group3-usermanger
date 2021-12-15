package it.fh.campus;

import com.google.common.flogger.FluentLogger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Rijndael {

    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final int MAX_KEY_LENGTH = 16;
    private static SecretKeySpec secretKey;
    private static String myKey = "superSecret123";

    private Rijndael(){
       throw new IllegalStateException("Rijndael class");
    }

    public static void setKey() {
        try {
            byte[] key = myKey.getBytes(StandardCharsets.UTF_8);
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, MAX_KEY_LENGTH);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException exception) {
            logger.at(Level.SEVERE).log("Error while set key: %s" + exception);
        }
    }

    public static String encrypt(String plaintext) {
        try {
            setKey();
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception exception) {
            logger.at(Level.SEVERE).log("Error while encrypting: %s" + exception);
        }
        throw new IllegalArgumentException("Error while encrypting");
    }

    public static String decrypt(String ciphertext) {
        try {
            setKey();
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(ciphertext)));
        } catch (Exception exception) {
            logger.at(Level.SEVERE).log("Error while decrypting: %s" + exception);
        }
        throw new IllegalArgumentException("Error while decrypting");
    }
}