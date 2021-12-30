package it.fh.campus.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RijndaelTest {

    public static final String PLAIN_TEXT ="12345";
    public static final String ENCRYPTED_TEXT = "zRaNireiGG1bPAYCevdp1w==";

    @Test
    void testEncrypt() {
        Assertions.assertEquals(ENCRYPTED_TEXT, Rijndael.encrypt(PLAIN_TEXT));
    }

    @Test
    void testDecrypt() {
        Assertions.assertEquals(PLAIN_TEXT, Rijndael.decrypt(ENCRYPTED_TEXT));
    }

}