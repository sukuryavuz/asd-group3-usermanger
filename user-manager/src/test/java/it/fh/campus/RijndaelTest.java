package it.fh.campus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test class for class Rijndael")
class RijndaelTest {

    private final String SECRET = "superSecret123";
    private final String PLAINTEXT = "Test";
    private final String CIPHERTEXT = "aY0I63EXL2PUo0aLXmdYcQ==";

    @BeforeEach
    void setUp(){
        Rijndael.setKey(SECRET);
    }

    @Test
    @DisplayName("Test method - Encryption of a plaintext")
    void testEncrypt(){
        //Arrange
        String result;
        //Act
        result = Rijndael.encrypt(PLAINTEXT, SECRET);
        //Assert
        assertEquals(CIPHERTEXT, result);
    }

    @Test
    @DisplayName("Test method - Decryption of a ciphertext")
    void testDecrypt(){
        //Arrange
        String result;
        //Act
        result = Rijndael.decrypt(CIPHERTEXT, SECRET);
        //Assert
        assertEquals(PLAINTEXT, result);
    }
}
