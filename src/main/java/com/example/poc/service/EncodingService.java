package com.example.poc.service;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import org.springframework.stereotype.Service;

/** This class is used to encode and decode the data. */
@Service
public class EncodingService {
  public String encode(String theString) {

    try {
      SecretKey claveAES = generateAESKey();
      IvParameterSpec iv = generateIV();

      return encryptData(theString, claveAES, iv);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private String encryptData(String input, SecretKey key, IvParameterSpec iv) throws Exception {
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE, key, iv);
    byte[] encrypted = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
    return Base64.getEncoder().encodeToString(encrypted);
  }

  public static String decrypt(String encryptedData, SecretKey key, IvParameterSpec iv)
      throws Exception {
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    cipher.init(Cipher.DECRYPT_MODE, key, iv);
    byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
    return new String(decrypted, StandardCharsets.UTF_8);
  }

  private IvParameterSpec generateIV() {
    byte[] iv = new byte[16];
    new SecureRandom().nextBytes(iv);
    return new IvParameterSpec(iv);
  }

  private SecretKey generateAESKey() throws NoSuchAlgorithmException {
    KeyGenerator keyGen = KeyGenerator.getInstance("AES");
    keyGen.init(128); // 128, 192 o 256 bits
    return keyGen.generateKey();
  }
}
