import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.BadPaddingException;

public class Utils {
    private static final String aglorithm = "SHA-256";
    private static final String transformation = "SHA-256";
    
    public static void encrypt(String key, File inputFile, File outputFile) throws NoSuchAlgorithmException, NoSuchPaddingException {
        Cipher test = Cipher.getInstance(key);
        test.
    }

}