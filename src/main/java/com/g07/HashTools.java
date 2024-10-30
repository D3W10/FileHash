package com.g07;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashTools {
    public static String encrypt(File file, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);

        try (InputStream fis = new FileInputStream(file)) {
            int bytesCount;
            byte[] byteArray = new byte[1024];

            while ((bytesCount = fis.read(byteArray)) != -1)
                digest.update(byteArray, 0, bytesCount);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        byte[] hashBytes = digest.digest();

        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1)
                hexString.append("0");

            hexString.append(hex);
        }

        return hexString.toString();
    }
}