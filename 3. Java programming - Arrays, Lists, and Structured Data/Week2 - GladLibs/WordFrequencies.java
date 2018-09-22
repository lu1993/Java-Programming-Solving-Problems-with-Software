
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies () {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique () {
        myWords.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();
        for (String s: resource.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1) {
                myWords.add(s);
                myFreqs.add(1);
            }
            else{
                int value = myFreqs.get(index);
                myFreqs.set(index, value + 1);
            }
        }
    }
    
    public void tester () {
        findUnique();
        System.out.println("# unique words: " + myWords.size());
        //for (int k = 0; k < myWords.size(); k ++) {
        //    System.out.println(myWords.get(k) + "\t"  + myFreqs.get(k));
        //}
        int maxIndex = findIndexOfMax();
        System.out.println("Word that has hightes frequency: " + 
                            myWords.get(maxIndex) + "\t"  + 
                            myFreqs.get(maxIndex));
    }
    
    public int findIndexOfMax () {
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
}
