
/**
 * Write a description of WordsInFile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import java.util.*;
import edu.duke.*;
import org.apache.commons.csv.*;

public class WordsInFile {
    private HashMap<String, ArrayList<String>> map;
    
    public WordsInFile () {
        map = new HashMap<String, ArrayList<String>>();
    }
    
    public void buildWordFileMap () {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            String fileName = f.getName();
            FileResource fr = new FileResource(f);
            for (String w: fr.words()) {
                if (map.containsKey(w)) {
                    ArrayList<String> arr = map.get(w);
                    if (!arr.contains(fileName)) {
                        arr.add(fileName);
                    }
                }
                else {
                    ArrayList<String> arr = new ArrayList<String>();
                    arr.add(fileName);
                    map.put(w, arr);
                }
            } 
        }
    }
    
    public int maxNumber () {
        int maxNum = 0;
        for (String k: map.keySet()) {
            if (maxNum < map.get(k).size()) {
                maxNum = map.get(k).size();
            }
        }
        return maxNum;
    }
    
    public ArrayList<String> wordsInNumFiles (int number) {
        ArrayList<String> arr = new ArrayList<String>();
        for (String k: map.keySet()) {
            if (map.get(k).size() == number) {
                arr.add(k);
            }
        }
        return arr;
    }
    
    public void testWordsInNumFiles () {
        WordsInFile wif = new WordsInFile();
        wif.buildWordFileMap();
        ArrayList<String> arr = wif.wordsInNumFiles(4);
        int size = arr.size();
        System.out.println(size);
        //for (String s: arr) {
            //System.out.println(s);
        //}
    }
    
    public void printFilesIn (String word) {
        for (String k: map.keySet()) {
            //System.out.println(k);
            if (k.equals(word)) {
            //if (k.toUpperCase().equals(word.toUpperCase())) {
                ArrayList<String> arr = map.get(k);
                for (String s: arr) {
                    System.out.println(s);
                }
                break;
            }
        }
    }
    
    public void testPrintFilesIn () {
        WordsInFile wif = new WordsInFile();
        wif.buildWordFileMap();
        //int size = wif.map.size();
        //System.out.println(size);
        wif.printFilesIn("tree");
    }
    
    public void tester () {
        buildWordFileMap ();
        printFilesIn("cat");
    }
}
