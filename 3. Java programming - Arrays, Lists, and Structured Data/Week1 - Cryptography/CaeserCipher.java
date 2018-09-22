
/**
 * Write a description of CaeserCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import org.apache.commons.csv.*;
import edu.duke.*;

public class CaeserCipher {
    public String encrypt (String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        
        for (int i = 0; i < input.length(); i ++) {
            char ch = encrypted.charAt(i);
            // check if character is lower case
            // if it is lower case, flag it and convert it to upper case
            boolean isLowerCase = false;
            if (!(ch == Character.toUpperCase(ch))) {
                isLowerCase = true;
                ch = Character.toUpperCase(ch);
            }
            int index = alphabet.indexOf(ch);
            if (index != -1) {
                char newChar = shiftedAlphabet.charAt(index);
                // if character is lower case originally, convert it back to lower case 
                if (isLowerCase) {
                    newChar = Character.toLowerCase(newChar);
                }
                encrypted.setCharAt(i, newChar);
            } 
        }
        return encrypted.toString();
    }
    
    public void testCaeser () {
        int key = 15;
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        //String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = encrypt(message, key);
        System.out.println("Encrypted message is " + encrypted);
        //String decrypted = encrypt(encrypted, 26 - key);
        //System.out.println("Decrypted message is " + decrypted);
    }
    
    public String encryptTwoKeys (String input, int key1, int key2) {
        String msg1 = halfString(input,0);
        String msg2 = halfString(input,1);
        String emsg1 = encrypt(msg1, key1);
        String emsg2 = encrypt(msg2, key2);
        StringBuilder result = new StringBuilder(input);
        for (int i = 0; i < input.length(); i ++) {
            if (i % 2 == 0) {
                result.setCharAt(i, emsg1.charAt(i / 2));
            }
            else {
                result.setCharAt(i, emsg2.charAt((i - 1) / 2));
            }
        }
        return result.toString();
    }
    
    public String halfString (String encrypted,int position) {
        String result = "";
        for (int i = 0; i < encrypted.length(); i ++) {
            if (i % 2 == position) {
                result = result + encrypted.charAt(i);
            }
        }
        return result;
    }
    
    public void testEncryptTwoKeys () {
        String message = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        String encrypted = encryptTwoKeys(message, 12, 2);
        System.out.println("Encrypted message is: " + encrypted);
    }
}
