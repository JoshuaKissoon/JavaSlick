package com.joshuakissoon.slick.encryption;

import com.joshuakissoon.slick.util.HashCalculator;
import java.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Class that handles encrypting the configuration
 *
 * @author Joshua Kissoon
 * @since 20150523
 */
public class AESEncryptor
{

    private final SecretKeySpec secretKey;
    private final IvParameterSpec iv;

    public AESEncryptor(final String key) throws NoSuchAlgorithmException
    {
        final byte[] keyHash = HashCalculator.md5Hash(key);
        secretKey = new SecretKeySpec(keyHash, "AES");

        iv = new IvParameterSpec(keyHash);
    }

    public String encrypt(final String plainTextMessage)
            throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
            BadPaddingException, InvalidAlgorithmParameterException
    {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, this.secretKey, iv);

        byte[] messageBytes = plainTextMessage.getBytes();

        byte[] raw = cipher.doFinal(messageBytes);

        return java.util.Base64.getEncoder().encodeToString(raw);
    }

    public String decrypt(final String encryptedMessage)
            throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
            BadPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException
    {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, this.secretKey, iv);

        byte[] raw = Base64.getDecoder().decode(encryptedMessage);

        byte[] stringBytes = cipher.doFinal(raw);

        String clearText = new String(stringBytes, "UTF8");
        return clearText;
    }
}
