package it.fh.campus.utilities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test class for class Rijndael")
class RijndaelTest {

    private final String PLAINTEXT = "Test";
    private final String CIPHERTEXT = "aY0I63EXL2PUo0aLXmdYcQ==";
    private String result;

    @Test
    @DisplayName("Test method - Encryption of a plaintext")
    void testEncrypt(){
        //Arrange

        //Act
        result = Rijndael.encrypt(PLAINTEXT);
        //Assert
        assertEquals(CIPHERTEXT, result);
    }

    @Test
    @DisplayName("Test method - Decryption of a ciphertext")
    void testDecrypt(){
        //Arrange

        //Act
        result = Rijndael.decrypt(CIPHERTEXT);
        //Assert
        assertEquals(PLAINTEXT, result);
    }
}
