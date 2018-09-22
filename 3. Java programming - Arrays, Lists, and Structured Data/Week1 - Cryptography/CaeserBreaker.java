
/**
 * Write a description of CaeserBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CaeserBreaker {
    public String decryptTwoKeys (String encrypted) {
        CaeserCipher cc1 = new CaeserCipher();
        CaeserCipher cc2 = new CaeserCipher();
        String msg1 = halfString(encrypted, 0);
        String msg2 = halfString(encrypted, 1);
        int key1 = getKey(msg1);
        int key2 = getKey(msg2);
        System.out.println("first key is " + key1);
        System.out.println("second key is " + key2);
        String dmsg1 = cc1.encrypt(msg1, (26 - key1));
        String dmsg2 = cc1.encrypt(msg2, (26 - key2));
        StringBuilder result = new StringBuilder(encrypted);
        for (int i = 0; i < encrypted.length(); i ++) {
            if (i % 2 == 0) {
                result.setCharAt(i, dmsg1.charAt(i / 2));
            }
            else {
                result.setCharAt(i, dmsg2.charAt((i - 1) / 2));
            }
        }
        return result.toString();
    }
    
    public void testDecryptTwoKeys () {
        //String encrypted = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        String result = decryptTwoKeys(encrypted);
        System.out.println(result);
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
    
    public void testHalfString () {
        String message = "abcd";
        String result = halfString(message, 0);
        System.out.println(result);
    }
    
    public int[] countLetters (String message) {
        int[] counts = new int[26];
        String alph = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < message.length(); i ++) {
            char curChar = message.charAt(i);
            for (int j = 0; j < alph.length(); j ++) {
                if (Character.toLowerCase(curChar) == Character.toLowerCase(alph.charAt(j))) {
                    counts[j] += 1;
                    break;
                }
            }
        }
        return counts;
    }
    
    public void testCountLetters () {
        String message = "aaaaabb";
        int[] counts = countLetters(message);
        String alph = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < alph.length(); i ++) {
            System.out.println("number of " + alph.charAt(i) + " is " + counts[i]);
        }
    }
    
    public int indexOfMax (int[] counts) {
        int maxValue = 0;
        int maxIndex = 0;
        for (int i = 0; i < counts.length; i ++) {
            if (counts[i] > maxValue) {
                maxValue = counts[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public void testIndexOfMax () {
        String message = "aaaaabb";
        int[] counts = countLetters(message);
        int key = indexOfMax(counts);
        System.out.println("max index is " + key);
    }
    
    public int getKey (String message) {
        int[] freq = countLetters(message);
        int key = indexOfMax(freq);
        int dkey = key - 4;
        if (key < 4) {
            dkey = 26 - (4 - key);
        }
        return dkey;
    }
    
    public void testGetKey () {
        String message = "aaaaabb";
        int key = getKey(message);
        System.out.println("key is " + key);
    }
}

