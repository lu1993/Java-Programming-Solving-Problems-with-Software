
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.util.*;
import org.apache.commons.csv.*;

public class CharactersInPlay {
    private ArrayList<String> characters; 
    private ArrayList<Integer> counts; 
    
    public CharactersInPlay () {
        characters = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }
    
    public void update (String person) {
        
        if (characters.contains(person)) {
            int index = characters.indexOf(person);
            int count = counts.get(index);
            counts.set(index, count + 1);
        }
        else {
            
            characters.add(person);
            counts.add(1);
        }
    }
    
    public void findAllCharacters () {
        characters.clear();
        counts.clear();
        FileResource fr = new FileResource();
        for (String line: fr.lines()) {
            if (line.contains(".")) {
                String person = line.split("\\.")[0].trim();
                if (!person.contains(" ")) {
                    // System.out.println("Character name is " + person);
                    update(person);
                }
            }
        }
    }
    
    public int findIndexOfMax (ArrayList<Integer> myFreqs) {
        int maxIndex = 0;
        int maxFreq = 0;
        for (int i = 0; i < myFreqs.size(); i ++) {
            int value = myFreqs.get(i);
            if (value > maxFreq) {
                maxFreq = value;
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public void testFindAllCharacters () {
        findAllCharacters();
        int maxIndex = findIndexOfMax(counts);
        String person = characters.get(maxIndex);
        int count = counts.get(maxIndex);
        System.out.println("character with the most speaking part " + person);
        System.out.println("number of speaking part " + count);
    }
    
    public void charactersWithNumParts (int num1, int num2) {
        System.out.println("Characters who have exactly " + num1 + "-" + num2 + " speaking parts:");
        int maxNum = 0;
        String person = "";
        for (int k = 0; k < characters.size(); k++) {
            int count = counts.get(k);
            if (count >= num1 && count <= num2) {
                System.out.println(characters.get(k) + " speaks " + count);
                if (count > maxNum) {
                    maxNum = count;
                    person = characters.get(k);
                }
            }
        }
        System.out.println("Characters who speak the most " + person);
    }
    
    public void testCharactersWithNumParts () {
        CharactersInPlay cip = new CharactersInPlay();
        cip.findAllCharacters();
        cip.charactersWithNumParts(0, 100);
        //cip.charactersWithNumParts(10, 15);
    }
    
    public void tester () {
        CharactersInPlay cip = new CharactersInPlay();
        cip.findAllCharacters();
        for (int k = 0; k < cip.characters.size(); k++) {
            System.out.println(cip.characters.get(k) + " speaks " + cip.counts.get(k));
            
        }
    }
}
