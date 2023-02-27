package org.example;

import javax.crypto.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

public class Encryption {
    private String originalWord;

    private void showScanner() {
        Scanner scanner = new Scanner(System.in);
        originalWord = scanner.nextLine();
    }


    public void encryptor() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, IOException {

        showScanner();

        Cipher cipher = Cipher.getInstance("AES");

        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        SecretKey key = kgen.generateKey();

        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] bytes = cipher.doFinal(originalWord.getBytes(StandardCharsets.UTF_8));

        BufferedWriter writer = new BufferedWriter(new FileWriter(
                "C:\\Users\\User\\Downloads\\ROT1-master\\src\\main\\java\\org\\example\\cipher.txt",
                true));

        for(int i=0; i < bytes.length; i++) {
            System.out.print(bytes[i]);
        }

        writer.write(Arrays.toString(bytes));
        writer.close();

        Cipher decryptCipher = Cipher.getInstance("AES");
        decryptCipher.init(Cipher.DECRYPT_MODE, key);

        System.out.println(" ");

        BufferedReader reader = new BufferedReader(new FileReader(
                "C:\\Users\\User\\Downloads\\ROT1-master\\src\\main\\java\\org\\example\\cipher.txt"));
        String currentLine = reader.readLine();

        byte[] decryptedBytes = decryptCipher.doFinal(bytes);
        String word = new String(decryptedBytes);

        reader.close();

        System.out.println(currentLine);
        System.out.println(word);
    }
}