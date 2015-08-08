package cl.entel.appswlsdesa.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by alex on 8/4/14.
 */
public class ARC4 {
    public static String encrypt(String cipherTxt) throws NoSuchPaddingException, NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException, ShortBufferException {
        byte[] input = cipherTxt.getBytes();
        byte[] keyBytes = "mzzo".getBytes();

        SecretKeySpec key = new SecretKeySpec(keyBytes, "ARC4");
        Cipher cipher = Cipher.getInstance("ARC4", "BC");

        // encryption pass
        byte[] cipherText = new byte[input.length];
        cipher.init(Cipher.ENCRYPT_MODE, key);
        cipher.update(input, 0, input.length, cipherText, 0);
        return byteArrayToHexString(cipherText);
    }

    final protected static char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    public static String byteArrayToHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length*2];
        int v;

        for(int j=0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j*2] = hexArray[v>>>4];
            hexChars[j*2 + 1] = hexArray[v & 0x0F];
        }

        return new String(hexChars);
    }
}